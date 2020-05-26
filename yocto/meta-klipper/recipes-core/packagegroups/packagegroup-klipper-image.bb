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
    "
