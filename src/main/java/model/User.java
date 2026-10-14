package boardease.model;

public record User(
        int id,
        String username,
        String password,
        String roles,    // ADMIN, STAFF, or TENANT[cite: 3]
        String status    // PENDING or ACTIVE[cite: 3]
) {}