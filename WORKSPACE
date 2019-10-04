workspace(name = "idea_settings")

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive", "http_jar")

BAZEL_DEPS_COMMIT = "e3f77e22d9f5b070915067a766607cfc96835c98"

BAZEL_DEPS_SHA256 = "5dac398f0dc57f76566642cf21f10960eed83f4bb56c5860170c0582f5581194"

CHECKSTYLE_IDEA_VERSION = "5.24.2"

CHECKSTYLE_IDEA_SHA256 = "1622761d25d318a8281732f0a16e805b44157161c6ffb3e88e6c3f79d9ca97fa"

BAZEL_INTELLIJ_COMMIT = "485a11132a7fd1b563357fa4263dd6ca3e3dd275"

BAZEL_INTELLIJ_SHA256 = "bc3187cc94852f2d846cc427b9c1c420aa3f8726886dfe223f98872a0f490e9b"

# To view newer releases: https://www.jetbrains.com/intellij-repository/releases/
IDEA_IC_VERSION = "2019.2"

IDEA_IC_SHA256 = "9567f2a88c9d4c4a0495208914f07bd2dace78dad0fee31fb9f8a4adab3cc437"

IDEA_IC_SOURCES_SHA256 = "0218f68cdc58d668ed2687f443719ba14c38fbf4a46ca2b7fda992b2a647acf8"

http_archive(
    name = "idea-IC",
    build_file = "idea-IC.BUILD",
    sha256 = IDEA_IC_SHA256,
    url = "https://www.jetbrains.com/intellij-repository/releases/com/jetbrains/intellij/idea/ideaIC/{0}/ideaIC-{0}.zip".format(IDEA_IC_VERSION),
)

http_jar(
    name = "idea-IC-sources",
    sha256 = IDEA_IC_SOURCES_SHA256,
    url = "https://www.jetbrains.com/intellij-repository/releases/com/jetbrains/intellij/idea/ideaIC/{0}/ideaIC-{0}-sources.jar".format(IDEA_IC_VERSION),
)

http_archive(
    name = "bazel-deps",
    sha256 = BAZEL_DEPS_SHA256,
    strip_prefix = "bazel-deps-{0}".format(BAZEL_DEPS_COMMIT),
    url = "https://github.com/johnynek/bazel-deps/archive/{0}.zip".format(BAZEL_DEPS_COMMIT),
)

http_archive(
    name = "CheckStyle-IDEA",
    build_file = "CheckStyle-IDEA.BUILD",
    sha256 = CHECKSTYLE_IDEA_SHA256,
    strip_prefix = "CheckStyle-IDEA",
    url = "https://plugins.jetbrains.com/files/1065/54249/checkstyle-idea-{0}.zip".format(CHECKSTYLE_IDEA_VERSION),
)

http_archive(
    name = "intellij_with_bazel",
    sha256 = BAZEL_INTELLIJ_SHA256,
    strip_prefix = "intellij-{0}".format(BAZEL_INTELLIJ_COMMIT),
    url = "https://github.com/bazelbuild/intellij/archive/{0}.zip".format(BAZEL_INTELLIJ_COMMIT),
)

http_archive(
    name = "rules_pkg",
    sha256 = "02de387c5ef874379e784ac968bf6efffe5285a168cab5a3169e08cfc634fd22",
    url = "https://github.com/bazelbuild/rules_pkg/releases/download/0.2.2/rules_pkg-0.2.2.tar.gz",
)

load("@rules_pkg//:deps.bzl", "rules_pkg_dependencies")

rules_pkg_dependencies()

# bazel-deps will soon produce a jar that can be used to run @bazel-deps//:parse from within
# this project.
load("//third_party:package-lock.bzl", "maven_dependencies")

maven_dependencies()

# Test dependencies
http_archive(
    name = "javapoet",
    build_file = "javapoet.BUILD",
    sha256 = "6b3fc0ae4e321286d09ce633037152a3194f7ebbd6d8b06a9f195fc7c9255d65",
    strip_prefix = "javapoet-javapoet-1.11.1",
    url = "https://github.com/square/javapoet/archive/javapoet-1.11.1.zip",
)

http_archive(
    name = "auto",
    build_file_content = """
package(default_visibility = ["//visibility:public"])
filegroup(
    name = "auto",
    srcs = glob(["**/*"]),
)""",
    sha256 = "c926daf62d24bb6ad6289e997d67dcacbb1054e6cc2d9ba4c406f509e7c20875",
    strip_prefix = "auto-auto-value-1.6.6",
    url = "https://github.com/google/auto/archive/auto-value-1.6.6.zip",
)

http_archive(
    name = "build_bazel_integration_testing",
    sha256 = "490554b98da4ce6e3e1e074e01b81e8440b760d4f086fccf50085a25528bf5cd",
    strip_prefix = "bazel-integration-testing-922d2b04bfb9721ab14ff6d26d4a8a6ab847aa07",
    url = "https://github.com/bazelbuild/bazel-integration-testing/archive/922d2b04bfb9721ab14ff6d26d4a8a6ab847aa07.zip",
)

load("@build_bazel_integration_testing//tools:bazel_java_integration_test.bzl", "bazel_java_integration_test_deps")

bazel_java_integration_test_deps()
