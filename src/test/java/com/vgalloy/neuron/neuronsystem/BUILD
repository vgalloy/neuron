package(default_visibility = ["//visibility:public"])

java_library(
    name = "helper",
    srcs = ["NeuronSystemTestHelper.java"],
    deps = [
        "//src/main/java:src",
        "//src/test/java/com/vgalloy/neuron/util:src",
        "@junit_junit//jar",
    ],
)

java_test(
    name = "neuron_system_builder",
    srcs = ["NeuronSystemBuilderTest.java"],
    test_class = "com.vgalloy.neuron.neuronsystem.NeuronSystemBuilderTest",
    size = "small",
    deps = [
        ":helper",
        "//src/main/java:src",
        "//src/test/java/com/vgalloy/neuron/util:src",
    ],
    testonly = False,
)

java_test(
    name = "neuron_system",
    srcs = ["NeuronSystemTest.java"],
    test_class = "com.vgalloy.neuron.neuronsystem.NeuronSystemTest",
    size = "small",
    deps = [
        ":helper",
        "//src/main/java:src",
        "//src/test/java/com/vgalloy/neuron/util:src",
    ],
    testonly = False,
)

java_test(
    name = "neuron_system_test_helper",
    srcs = ["NeuronSystemTestHelperTest.java"],
    test_class = "com.vgalloy.neuron.neuronsystem.NeuronSystemTestHelperTest",
    size = "small",
    deps = [
        ":helper",
        "//src/main/java:src",
        "//src/test/java/com/vgalloy/neuron/util:src",
    ],
    testonly = False,
)

java_test(
    name = "xor_test",
    srcs = ["XorTest.java"],
    test_class = "com.vgalloy.neuron.neuronsystem.XorTest",
    size = "small",
    deps = [
        ":helper",
        "//src/main/java:src",
        "//src/test/java/com/vgalloy/neuron/util:src",
    ],
    testonly = False,
)

filegroup(
    name = "all_test",
    srcs = [
        ":neuron_system_builder",
        ":neuron_system",
        ":neuron_system_test_helper",
        ":xor_test",
    ],
)
