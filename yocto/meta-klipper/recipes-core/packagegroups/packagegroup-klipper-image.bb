DESCRIPTION = "Klipper image dependency metapackage"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

RDEPENDS_${PN} = "\
    e2fsprogs-e2fsck \
    e2fsprogs-resize2fs \
    kernel-devicetree \
    klipper \
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
    ntpdate \
    "

RDEPENDS_${PN}_append_tinker_board_s = "\
    kernel-image-zimage \
    "

RDEPENDS_${PN}_append_cubietruck = "\
    kernel-image-uimage \
    "
