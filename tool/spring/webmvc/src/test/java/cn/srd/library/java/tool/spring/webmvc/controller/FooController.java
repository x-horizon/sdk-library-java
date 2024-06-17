// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.spring.webmvc.controller;

import cn.srd.library.java.tool.spring.webmvc.model.vo.FooVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author wjm
 * @since 2024-06-15 14:54
 */
@RestController
@RequestMapping("/foo")
public class FooController {

    @PostMapping("/sayHello1")
    public void sayHello1(@Validated @RequestBody FooVO fooVO) {
        System.out.println(fooVO);
    }

    @RequestMapping(path = "/sayHello2", method = {RequestMethod.GET, RequestMethod.POST})
    public void sayHello2(@RequestParam(required = true) Long id, @RequestParam(required = true) String name) {
        System.out.println(id + name);
    }

}