


# FORCE QNX compiler
set(CMAKE_C_COMPILER   "$ENV{QNX_HOST}/usr/bin/qcc")
set(CMAKE_CXX_COMPILER "$ENV{QNX_HOST}/usr/bin/q++")

# Tell CMake where the QNX sysroot is (CRITICAL)
set(CMAKE_SYSROOT "$ENV{QNX_TARGET}/$ENV{CPUVARDIR}")

# Force find_package to look ONLY inside the QNX target
set(CMAKE_FIND_ROOT_PATH "${CMAKE_SYSROOT}")
set(CMAKE_FIND_ROOT_PATH_MODE_PROGRAM NEVER)
set(CMAKE_FIND_ROOT_PATH_MODE_LIBRARY ONLY)
set(CMAKE_FIND_ROOT_PATH_MODE_INCLUDE ONLY)

# Boost: qnx-ports installs it here
set(BOOST_INCLUDEDIR "${CMAKE_SYSROOT}/usr/local/include")
set(BOOST_LIBRARYDIR "${CMAKE_SYSROOT}/usr/local/lib")

cmake_minimum_required(VERSION 3.13)
project(msm-vsomeip-qnx LANGUAGES CXX)

# vsomeip3 config is here
set(CMAKE_PREFIX_PATH "${CMAKE_SYSROOT}/usr/local/lib/cmake/vsomeip3")

find_package(vsomeip3 REQUIRED)
find_package(Boost REQUIRED COMPONENTS system thread)

add_executable(msm-service src/msm-service.cpp)
target_link_libraries(msm-service vsomeip3 Boost::system Boost::thread)

add_executable(msm-client src/msm-client.cpp)
target_link_libraries(msm-client vsomeip3 Boost::system Boost::thread)

target_compile_options(msm-service PRIVATE -g -O0)
target_compile_options(msm-client   PRIVATE -g -O0)
