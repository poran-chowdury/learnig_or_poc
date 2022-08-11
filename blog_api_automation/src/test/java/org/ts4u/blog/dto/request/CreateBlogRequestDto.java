package org.ts4u.blog.dto.request;

import lombok.Builder;
import lombok.Data;

/**
 * @author TOWFIQUL ISLAM
 * @email towfiq.106@gmail.com
 * @since 11/28/21 12:55 AM
 */

@Builder
@Data
public class CreateBlogRequestDto {

    private String name;

    private String description;
}
