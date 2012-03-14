============
Introduction
============

Thumbor Enterprise Edition (a.k.a thumborEE) is a library to generate encrypted
URLs for thumbor.

In order to use it, just download the latest version from the listed versions
below in the Downloads section of the readme file and add it to your project.

=====
Usage
=====

Using thumborEE is pretty simple, all you have to do is create a CryptoURL
instance with the proper key and original image URL and start customizing it.

.. code-block:: ruby
    CryptoURL crypto = new CryptoURL('my-security-key', 'http://my.server.com/path/to/my/image.jpg');
