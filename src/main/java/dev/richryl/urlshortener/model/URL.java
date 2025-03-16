package dev.richryl.urlshortener.model;

import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("urls")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@TypeAlias("url")
public class URL {
    @Id
    private String shortCode;
    @Pattern(regexp = "^(http|https)://.*$", message = "URL must start with http:// or https://")
    private String url;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long accessCount = 0L;

    public void setShortCode(String shortCode) {
        if(shortCode == null || shortCode.isEmpty())
            throw new IllegalArgumentException("Short code cannot be null or empty");
        this.shortCode = shortCode;
    }

    @Override
    public String toString() {
        return "URL{" +
                "shortCode='" + shortCode + '\'' +
                ", url='" + url + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", accessCount=" + accessCount +
                '}';
    }
}
