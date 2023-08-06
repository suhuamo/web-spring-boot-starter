package com.yscz.test.pojo;
import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

/**
* <p>
*  数据表实体类
* </p>
*
* @author suhuamo
* @date 2023-08-06
* @slogan 也许散落在浩瀚宇宙的小行星们也知道
*/
@NoArgsConstructor
@Data
@FieldNameConstants
@Accessors(chain = true)
@Entity
@Table(name = "tbl_dept")
public class Dept implements Serializable {
    private static final long serialVersionUID=1L;

    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "dept_name")
    private String deptName;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
