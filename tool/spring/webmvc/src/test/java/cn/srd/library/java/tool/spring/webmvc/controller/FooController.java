// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.spring.webmvc.controller;

import cn.srd.library.java.tool.spring.webmvc.model.vo.FooVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wjm
 * @since 2024-06-15 14:54
 */
@RestController
@RequestMapping("/testSpringWebMvc")
public class FooController {

    @PostMapping("/hello")
    public void sayHello(@RequestBody FooVO fooVO) {
        System.out.println(fooVO);
    }

}