package com.example.servermanagementapi;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servers")
public class ServerController {
    private final ServerRepository serverRepository;

    public ServerController(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    @GetMapping
    public List<Server> getAllServers() {
        return serverRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Server> getServerById(@PathVariable String id) {
        return serverRepository.findById(id)
                .map(server -> ResponseEntity.ok().body(server))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Server> createServer(@RequestBody Server server) {
        Server savedServer = serverRepository.save(server);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedServer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Server> updateServer(@PathVariable String id, @RequestBody Server updatedServer) {
        return serverRepository.findById(id)
                .map(server -> {
                    updatedServer.setId(server.getId());
                    Server savedServer = serverRepository.save(updatedServer);
                    return ResponseEntity.ok().body(savedServer);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServer(@PathVariable String id) {
        serverRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<Server> searchServersByName(@RequestParam String name) {
        return serverRepository.findByNameContaining(name);
    }
}
