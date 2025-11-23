SUMMARY = "recipe to build my vsomeip app {client-service}"
DESCRIPTION = "vsomeip client/service app"

LICENSE = "CLOSED"

# ----------------------------------------------------------------------------------------
# ------ fetch & unpack

FILESEXTRAPATHS:prepend := "${THISDIR}:"
SRC_URI = "file://CMakeLists.txt \
           file://msm-client.json \
           file://msm-service.json \
           file://src/msm-client.cpp \
           file://src/msm-service.cpp"


# ----------------------------------------------------------------------------------------
# ------ configure

S = "${WORKDIR}"

# ----------------------------------------------------------------------------------------
# ------ compile

DEPENDS += "vsomeip boost systemd"

inherit cmake

EXTRA_OECMAKE = "-DCMAKE_INSTALL_PREFIX=${prefix} \
                 -DCMAKE_INSTALL_LIBDIR=${libdir} \
                 -DCMAKE_PREFIX_PATH=${STAGING_DIR_TARGET}/usr \
                 -DSYSCONFDIR=${prefix}/etc"

# ----------------------------------------------------------------------------------------
# ------ install

do_install:append() {
    install -d ${D}${prefix}/etc/vsomeip
    install -m 0644 ${WORKDIR}/msm-client.json ${D}${prefix}/etc/vsomeip/
    install -m 0644 ${WORKDIR}/msm-service.json ${D}${prefix}/etc/vsomeip/
}

# ----------------------------------------------------------------------------------------
# ------  QA

FILES:${PN} = "${bindir}/msm-client.cpp \
               ${bindir}/msm-service.cpp \
               ${prefix} \
               ${bindir}/* \
               ${libdir}/* \ 
               ${prefix}/etc/vsomeip/*"

