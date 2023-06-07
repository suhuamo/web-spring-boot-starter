package com.suhuamo.web.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author suhuamo
 * @date 2023-05-28
 * @slogan 也许散落在浩瀚宇宙的小行星们也知道
 * 用于接受删除的接口的实体类
 */
@Data
public class DeleteDTO implements Serializable {

    /**
     * id
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}
