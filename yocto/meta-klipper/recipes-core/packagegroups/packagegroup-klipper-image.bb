DESCRIPTION = "Klipper image dependency metapackage"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

RDEPENDS_${PN} = "\
    e2fsprogs-e2fsck \
    e2fsprogs-resize2fs \
    kernel-image-zimage \
    kernel-devicetree \
    klipper \
    octoprint \
    lighttpd \
    avahi-daemon \
    avahi-autoipd \
    tzdata \
    tzdata-africa \
    tzdata-americas \
    tzdata-antarctica \
    tzdata-antarctica \
    tzdata-asia \
    tzdata-atlantic \
    tzdata-australia \
    tzdata-europe \
    tzdata-pacific \
    ntp \
    "
