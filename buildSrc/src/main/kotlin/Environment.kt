// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

enum class Environment(val code: String, val handler: EnvironmentHandler) {

    LOCALHOST("localhost", EnvironmentLocalhostHandler),
    DEVELOPMENT("development", EnvironmentDevelopmentHandler),

    ;

}