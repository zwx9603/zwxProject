package com.dscomm.iecs.out.graphql.typebean;

import lombok.Data;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/23 16:46
 * @Describe
 */
@Data
public class TokenDate {
  private String access_token;
  private String token_type;
  private String username;
}
