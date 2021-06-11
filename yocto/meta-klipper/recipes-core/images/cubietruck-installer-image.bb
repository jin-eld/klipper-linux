DESCRIPTION = "Small image capable of booting a cubietruck with support for the \
Minimal MTD Utilities, which let the user interact with the MTD subsystem in \
the kernel to perform operations on flash devices."

PR = "r1"

COMPATIBLE_MACHINE = "cubietruck"

IMAGE_INSTALL = "packagegroup-core-boot \
    ${CORE_IMAGE_EXTRA_INSTALL} \
    mtd-utils \
    dosfstools \
    e2fsprogs-e2fsck \
    e2fsprogs-mke2fs \
    e2fsprogs-tune2fs \
    rsync \
    util-linux-mkfs \
    sunxi-tools \
    mtd-utils-ubifs \
    mtd-utils-misc \
    "

IMAGE_LINGUAS = " "

LICENSE = "MIT"

inherit core-image

IMAGE_ROOTFS_SIZE="1384000"

IMAGE_FEATURES += "ssh-server-openssh"
