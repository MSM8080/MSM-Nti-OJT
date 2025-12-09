# **MSM-BrightSkies-OJT**
# **vsomeip OTA**

### Description ![System](1.png)
The purpose is to build an OTA updating system for Rpi 4 running customized Linux image using
vsomeip protocol with QNX system that validate this update received from PC {Linux image
builder} using FTP protocol

### Hardware used:
  - Device (1): PC {linux imgae builder}
  - Device (2): V.M {Running QNX}
  - Device (3): Raspberri pi 4 {target H.W to be upadted remotly}

### The upadte (Payload):
 - Part (1): Image by yocto {linux kernel + u-boot}
 - Part (2): C++/QT application

### Communication protocls used:
 - Protocol (1): FTP between Device (1) & Device (2)
 - Protocol (2): VSOME/IP (usibg CommonApi) between Device (2) & Device (3)

## Integrity check:
 - Method: Cyclic redundancy check (CRC) check
 - Function: message digest algorithm 5 (md5)



