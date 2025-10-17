import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.Headers;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.*;
import java.time.Instant;
import java.util.UUID;
import java.util.stream.Collectors;

public class McpServer {
    private static final Path LOG_DIR = Paths.get("/tmp/mcp_logs");

    public static void main(String[] args) throws Exception {
        Files.createDirectories(LOG_DIR);
    int port = 8000;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/ingest", McpServer::handleIngest);
        server.createContext("/logs", McpServer::handleList);
        System.out.println("[MCP] Server listening on http://0.0.0.0:" + port);
        server.start();
    }

    private static void handleIngest(HttpExchange ex) {
        try {
            if (!"POST".equalsIgnoreCase(ex.getRequestMethod())) {
                send(ex, 405, "Only POST allowed");
                return;
            }
            byte[] body = readAllBytes(ex.getRequestBody());
            String ts = Instant.now().toString().replaceAll("[:.]", "-");
            String fname = ts + "-" + UUID.randomUUID().toString() + ".log";
            Path out = LOG_DIR.resolve(fname);
            Files.write(out, body, StandardOpenOption.CREATE_NEW);
            System.out.println("[MCP] Received " + body.length + " bytes -> " + out);
            String json = "{\"status\":\"ok\",\"path\":\"" + out.toString() + "\"}";
            Headers h = ex.getResponseHeaders();
            h.add("Content-Type", "application/json");
            send(ex, 200, json);
        } catch (Exception e) {
            e.printStackTrace();
            try { send(ex, 500, "{\"status\":\"error\"}"); } catch (IOException ignored) {}
        }
    }

    private static void handleList(HttpExchange ex) {
        try {
            if (!"GET".equalsIgnoreCase(ex.getRequestMethod())) {
                send(ex, 405, "Only GET allowed");
                return;
            }
            String list = Files.list(LOG_DIR)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .sorted()
                    .collect(Collectors.joining("\n"));
            Headers h = ex.getResponseHeaders();
            h.add("Content-Type", "text/plain; charset=utf-8");
            send(ex, 200, list);
        } catch (Exception e) {
            e.printStackTrace();
            try { send(ex, 500, "error"); } catch (IOException ignored) {}
        }
    }

    private static byte[] readAllBytes(InputStream in) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[8192];
        int r;
        while ((r = in.read(buf)) != -1) baos.write(buf, 0, r);
        return baos.toByteArray();
    }

    private static void send(HttpExchange ex, int code, String body) throws IOException {
        byte[] bytes = body.getBytes("UTF-8");
        ex.sendResponseHeaders(code, bytes.length);
        try (OutputStream os = ex.getResponseBody()) {
            os.write(bytes);
        }
    }
}
