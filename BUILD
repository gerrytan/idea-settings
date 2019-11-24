package(default_visibility = ["//visibility:public"])

load(
    "@intellij_with_bazel//build_defs:build_defs.bzl",
    "intellij_plugin",
    "intellij_plugin_library",
)
load("@rules_pkg//:pkg.bzl", "pkg_tar")

intellij_plugin(
    name = "idea-settings",
    plugin_xml = "src/main/resources/META-INF/plugin.xml",
    deps = [":plugin_library"],
)

intellij_plugin_library(
    name = "plugin_library",
    deps = ["//src/main/java/com/github/alexandrecarlton/idea/settings/starter"],
)

pkg_tar(
    name = "plugins",
    srcs = [
        ":idea-settings",
    ],
    files = {
        "@CheckStyle-IDEA//:lib/checkstyle-idea.jar": "CheckStyle-IDEA/lib/checkstyle-idea.jar",
        "@WebStorm//:plugins/fileWatcher/lib/fileWatcher.jar": "fileWatcher/lib/fileWatcher.jar",
        "@WebStorm//:plugins/fileWatcher/lib/resources_en.jar": "fileWatcher/lib/resources_en.jar",
    },
)

# For some reason referencing :plugins in sh_binary doesn't work;
# however, creating a filegroup and referencing that does.
# See https://github.com/bazelbuild/bazel/pull/6352
filegroup(
    name = "plugins_tar",
    srcs = [":plugins"],
)

sh_binary(
    name = "apply-idea-settings",
    srcs = ["apply-idea-settings.sh"],
    data = [
        ":idea.sh",
        ":idea64.vmoptions",
        ":plugins_tar",
    ],
    deps = ["@bazel_tools//tools/bash/runfiles"],
)

alias(
    name = "idea.sh",
    actual = select({
        "community": "@idea-IC//:bin/idea.sh",
        "ultimate": "@idea-IU//:bin/idea.sh",
    }),
)

alias(
    name = "idea64.vmoptions",
    actual = select({
        "community": "@idea-IC//:bin/linux/idea64.vmoptions",
        "ultimate": "@idea-IU//:bin/linux/idea64.vmoptions",
    }),
)

config_setting(
    name = "community",
    values = {"define": "product=community"},
)

config_setting(
    name = "ultimate",
    values = {"define": "product=ultimate"},
)

test_suite(
    name = "tests",
    tests = [
        "//src/test/kotlin/com/github/alexandrecarlton/idea/settings/applier/impl/build_execution_deployment",
        "//src/test/kotlin/com/github/alexandrecarlton/idea/settings/applier/impl/build_execution_deployment/build_tools/maven",
        "//src/test/kotlin/com/github/alexandrecarlton/idea/settings/applier/impl/build_execution_deployment/compiler",
        "//src/test/kotlin/com/github/alexandrecarlton/idea/settings/applier/impl/configurations/docker",
        "//src/test/kotlin/com/github/alexandrecarlton/idea/settings/applier/impl/configurations/remote",
        "//src/test/kotlin/com/github/alexandrecarlton/idea/settings/applier/impl/configurations/shell_script",
        "//src/test/kotlin/com/github/alexandrecarlton/idea/settings/applier/impl/configurations/spring_boot",
        "//src/test/kotlin/com/github/alexandrecarlton/idea/settings/applier/impl/editor/codestyle/java/arrangement",
        "//src/test/kotlin/com/github/alexandrecarlton/idea/settings/applier/impl/editor/codestyle/java/blank_lines",
        "//src/test/kotlin/com/github/alexandrecarlton/idea/settings/applier/impl/editor/codestyle/java/imports",
        "//src/test/kotlin/com/github/alexandrecarlton/idea/settings/applier/impl/editor/codestyle/java/javadoc",
        "//src/test/kotlin/com/github/alexandrecarlton/idea/settings/applier/impl/editor/codestyle/java/wrapping_and_braces",
        "//src/test/kotlin/com/github/alexandrecarlton/idea/settings/applier/impl/editor/codestyle/javascript",
        "//src/test/kotlin/com/github/alexandrecarlton/idea/settings/applier/impl/editor/general/auto_import",
        "//src/test/kotlin/com/github/alexandrecarlton/idea/settings/applier/impl/editor/spelling",
        "//src/test/kotlin/com/github/alexandrecarlton/idea/settings/applier/impl/languages_frameworks/javascript",
        "//src/test/kotlin/com/github/alexandrecarlton/idea/settings/applier/impl/languages_frameworks/sql_dialects",
        "//src/test/kotlin/com/github/alexandrecarlton/idea/settings/applier/impl/other_settings/checkstyle",
        "//src/test/kotlin/com/github/alexandrecarlton/idea/settings/applier/impl/project_settings/project",
        "//src/test/kotlin/com/github/alexandrecarlton/idea/settings/applier/impl/tools/file_watchers",
    ],
)

test_suite(
    name = "integration-tests",
    tests = [
        "//src/test/java/com/github/alexandrecarlton/idea/settings/integration",
    ],
)
