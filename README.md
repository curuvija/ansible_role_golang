Ansible Role: Golang
====================

Installs Golang in /usr/local/go folder.

Requirements
------------

No requirements.

Role Variables
--------------
Available variables are listed below, along with default values (see defaults/main.yml):

```yaml

golang_version: "1.20.5"
golang_package_sha256: "d7ec48cde0d3d2be2c69203bc3e0a44de8660b9c09a6e85c4732a3f7dc442612"
golang_path: "/usr/local"
```

Dependencies
------------

None.

Example Playbook
----------------

Including an example of how to use your role (for instance, with variables passed in as parameters) is always nice for users too:

```yaml
- hosts: all

  vars:
    golang_path: "/opt/golang"

  roles:
    - curuvija.golang

```

License
-------

MIT / BSD

Author Information
------------------

This role was created by Milos Curuvija in 2023.
