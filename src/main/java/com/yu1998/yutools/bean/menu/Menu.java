package com.yu1998.yutools.bean.menu;

import lombok.Data;

import java.util.List;

/**
 * @author Duke_yzl
 * @date 20230301.01
 * @describe：
 */
@Data
public class Menu{
    /** 长度为1-3 */
    List<Button> button;
}