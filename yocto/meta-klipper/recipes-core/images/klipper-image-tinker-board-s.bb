COMPATIBLE_MACHINE = "tinker-board-s"

inherit image

WKS_FILE = "tinker-board-s.wks.in"

IMAGE_FSTYPES = "wic.bz2 wic.bmap"

IMAGE_BASENAME = "klipper-image"

do_image_wic[depends] += "\
    virtual/bootloader:do_deploy \
    virtual/kernel:do_deploy \
    klipper-linux-rootfs:do_image_complete"

