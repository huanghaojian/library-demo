package xyz.hhjian.lib.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>豆瓣错误</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.11.05
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DouBanDTO {
    @JsonProperty("isbn13")
    private String isbn;
    @JsonProperty("publisher")
    private String publisher;
    @JsonProperty("image")
    private String image;
    @JsonProperty("title")
    private String title;
    @JsonProperty("pubdate")
    private String pubdate;
    @JsonProperty("author")
    private List<String> author;
    @JsonProperty("catalog")
    private String category;
}
