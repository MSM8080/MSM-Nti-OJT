# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "Unknown"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9741c346eef56131163e13b9db1241b3"

SRC_URI = "git://github.com/COVESA/vsomeip.git;protocol=https;branch=master"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "f58ba578c8c04e02dcf08d3ebcb9a71ca1e203ea"

S = "${WORKDIR}/git"

# NOTE: unable to map the following CMake package dependencies: benchmark Doxygen
DEPENDS = "systemd boost"

inherit cmake pkgconfig

# msm ------------------------------------------------------

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = "-DCMAKE_INSTALL_PREFIX=${prefix} \
                 -DCMAKE_INSTALL_LIBDIR=${libdir} \
                 -DSYSCONFDIR=${prefix}/etc"
# Install
# do_install:append() {
#          install -d {D}${bindir}
#          install -m 0755 ${WORKDIR}/vsomeip-1.0+git999/examples/hello_world/hello* ${D}${bindir}
# }

# # QA assurance
FILES:${PN}:append = " /usr/bin \
                        /usr/bin/* \
                        /usr/etc \ 
                        /usr/etc/vsomeip \
                        /usr/etc/vsomeip/vsomeip-tcp-service.json \
                        /usr/etc/vsomeip/vsomeip-udp-service.json \
                        /usr/etc/vsomeip/vsomeip-tcp-client.json \
                        /usr/etc/vsomeip/vsomeip.json \
                        /usr/etc/vsomeip/vsomeip-udp-client.json \
                        /usr/etc/vsomeip/vsomeip-local.json "


# hossam ------------------------------------------------------

#EXTRA_OECMAKE = "\
#    -DCMAKE_INSTALL_PREFIX=${prefix} \
#    -DCMAKE_INSTALL_LIBDIR=${libdir} \
#    -DSYSCONFDIR=${prefix}/etc \
#"

# Only append additional config files
#do_install:append() {
#    install -d ${D}${prefix}/etc/vsomeip
#    # install -m 0644 ${WORKDIR}/client.json ${D}${prefix}/etc/vsomeip/
#    # install -m 0644 ${WORKDIR}/service.json ${D}${prefix}/etc/vsomeip/
#}

#FILES:${PN} += "${bindir}/* ${bindir} ${libdir}/* ${prefix}/etc/vsomeip/*"
