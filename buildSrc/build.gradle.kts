// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

plugins {
    kotlin("jvm") version "1.9.20"
}

repositories {
    mavenLocal()
    maven("https://maven.aliyun.com/repository/public/")
    maven("https://maven.aliyun.com/repository/spring/")
    maven("https://repo.spring.io/milestone/")
    maven("https://repo.spring.io/snapshot/")
    mavenCentral()
}