// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

module cn.srd.library.java.orm.mybatis.flex.base {

    requires com.google.errorprone.annotations;
    requires java.sql;
    requires mybatis.flex.core;
    requires org.mapstruct;
    requires org.mybatis;
    requires spring.tx;
    requires cn.srd.library.java.contract.constant;
    requires cn.srd.library.java.contract.model;
    requires cn.srd.library.java.orm.contract;
    requires cn.srd.library.java.tool.lang;

    exports cn.srd.library.java.orm.mybatis.flex.base.audit;
    exports cn.srd.library.java.orm.mybatis.flex.base.converter;
    exports cn.srd.library.java.orm.mybatis.flex.base.dao;
    exports cn.srd.library.java.orm.mybatis.flex.base.id;
    exports cn.srd.library.java.orm.mybatis.flex.base.listener;
    exports cn.srd.library.java.orm.mybatis.flex.base.lock;
    exports cn.srd.library.java.orm.mybatis.flex.base.logic;
    exports cn.srd.library.java.orm.mybatis.flex.base.property;
    exports cn.srd.library.java.orm.mybatis.flex.base.query;
    exports cn.srd.library.java.orm.mybatis.flex.base.update;

}