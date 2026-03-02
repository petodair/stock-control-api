package br.com.stock_control_api.security;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimitingService {

    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    public Bucket resolveBucket(String ip) {
        return cache.computeIfAbsent(ip, this::newBucket);
    }

    private Bucket newBucket(String ip) {
        Bandwidth limit = Bandwidth.builder()
                .capacity(20)
                .refillIntervally(20, Duration.ofMinutes(1))
                .build();

        return Bucket.builder()
                .addLimit(limit)
                .build();
    }
}
