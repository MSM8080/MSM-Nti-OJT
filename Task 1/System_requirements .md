# System Requirments

## Hardware used:
  - Device (1): PC {linux imgae builder}
  - Device (2): just one of them:
      - Raspberri pi 3 {Running QNX}
        OR
      - V.M {Running QNX}
  - Device (3): Raspberri pi 4 {target H.W to be upadted remotly}

## The upadte (Payload):
 - Part (1): Image by yocto {linux kernel + u-boot}
 - Part (2): C++/QT application

## Communication protocls used:
 - Protocol (1): FTP between Device (1) & Device (2)
 - Protocol (2): SOME/IP between Device (2) & Device (3)
 - Protocol (3): V.M Shared directory 

## Integrity check:
 - Method: Cyclic redundancy check (CRC) check
 - Function: message digest algorithm 5 (md5)

## Description [System](./1.png)
  - Step (1): Building C++/Qt application for device (3) on device (1)
  - Step (2): Building the image with app by yocto (payload) on device (1)
  - Step (3): Generate CRC code for payload on device (1)
  - Step (4):
      - If device (2) is raspberry pi 3, Sending the payload to device (2) using protocol (1) over wifi
      - If device (2) is V.M , Sending the payload to device (2) using protocol (3) locally on device (1)
  - Step (5): Generate CRC code for payload on device (2)
  - Step (6): Compare generated CRC code for payload between device (1) & device (2)
  - Step (7): Notifying using protocol (2) device (3) that there is a new update for payload over wifi
  - Step (8): Device (3) using protocol (2) request upadte version of payload & compare it with current version of payload over wifi
  - Step (9): Device (2) using protocol (2) response with upadte payload version over wifi
  - Step (10): 
      - If current payload version on device (3) is new, no upadte happend
      - If current payload version on device (3) is old, device (2) using protocol (2) request update payload from device (2) over wifi
  - Step (11): Device (2) using protocol (2) response with upadte payload version over wifi
  - Step (12): Device (3) applys upadte payload in its file system and then perform a reboot

