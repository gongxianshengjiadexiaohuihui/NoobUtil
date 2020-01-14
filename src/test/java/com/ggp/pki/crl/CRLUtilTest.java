package com.ggp.pki.crl;

import org.junit.Assert;
import org.junit.Test;

import java.security.cert.X509CRL;
import java.util.List;


/**
 * @author: ggp
 * @Date: 2020/1/14 09:45
 * @Description:
 */
public class CRLUtilTest {
    public static String base64Str = "-----BEGIN X509 CRL-----\n" +
            "MIIndDCCJlwCAQEwDQYJKoZIhvcNAQELBQAwLzELMAkGA1UEBhMCY24xDDAKBgNV\n" +
            "BAoMA1JTQTESMBAGA1UEAwwJ5qC5Q0ExMTIxFw0yMDAxMTAwNzA0MzdaFw0yMDAx\n" +
            "MTAwNzA5MzdaMIIlijAjAgQQAAAXFw0xOTExMjgwNjMxMDBaMAwwCgYDVR0VBAMK\n" +
            "AQkwIwIEEAAAGBcNMTkxMTI4MDYzMTAwWjAMMAoGA1UdFQQDCgEJMCMCBBAAABsX\n" +
            "DTE5MTIwNjA2Mjc1OVowDDAKBgNVHRUEAwoBATAjAgQQAAAcFw0xOTEyMDYwNjI3\n" +
            "NTlaMAwwCgYDVR0VBAMKAQEwIwIEEAAAHRcNMTkxMjI1MDEyOTMyWjAMMAoGA1Ud\n" +
            "FQQDCgEEMCMCBBAAAB4XDTE5MTIyNTAxMjkzMlowDDAKBgNVHRUEAwoBBDAjAgQQ\n" +
            "AAB1Fw0xOTEyMDYwNjE3NTlaMAwwCgYDVR0VBAMKAQEwIwIEEAAAdhcNMTkxMjA2\n" +
            "MDYxNzU5WjAMMAoGA1UdFQQDCgEBMCMCBBAAAH8XDTE5MTIwNjA2MzAwMFowDDAK\n" +
            "BgNVHRUEAwoBCTAjAgQQAACAFw0xOTEyMDYwNjMwMDBaMAwwCgYDVR0VBAMKAQkw\n" +
            "IwIEEAAAgRcNMTkxMjA2MDYzMDAwWjAMMAoGA1UdFQQDCgEJMCMCBBAAAIIXDTE5\n" +
            "MTIwNjA2MzAwMFowDDAKBgNVHRUEAwoBCTAjAgQQAACDFw0xOTEyMTIxMjMyMTNa\n" +
            "MAwwCgYDVR0VBAMKAQEwIwIEEAAAhBcNMTkxMjEyMTIzMjEzWjAMMAoGA1UdFQQD\n" +
            "CgEBMCMCBBAAAMoXDTE5MTIxOTA3MjQxOVowDDAKBgNVHRUEAwoBBDAjAgQQAADL\n" +
            "Fw0xOTEyMTkwNzI0MTlaMAwwCgYDVR0VBAMKAQQwIwIEEAAA0BcNMTkxMjIzMDgy\n" +
            "NTAwWjAMMAoGA1UdFQQDCgEJMCMCBBAAANEXDTE5MTIyMzA4MjUwMFowDDAKBgNV\n" +
            "HRUEAwoBCTAVAgQQAADUFw0xOTEyMjMwODQ4MjZaMBUCBBAAANUXDTE5MTIyMzA4\n" +
            "NDgyNlowFQIEEAAA2BcNMTkxMjIzMDg1NDAwWjAVAgQQAADZFw0xOTEyMjMwODU0\n" +
            "MDBaMCMCBBAAAN4XDTE5MTIyNDAxMjMzN1owDDAKBgNVHRUEAwoBATAjAgQQAADf\n" +
            "Fw0xOTEyMjQwMTIzMzdaMAwwCgYDVR0VBAMKAQEwIwIEEAAA4BcNMTkxMjIzMDkx\n" +
            "ODIxWjAMMAoGA1UdFQQDCgEEMCMCBBAAAOEXDTE5MTIyMzA5MTgyMVowDDAKBgNV\n" +
            "HRUEAwoBBDAjAgQQAADwFw0xOTEyMjQwNjQ2MDBaMAwwCgYDVR0VBAMKAQkwIwIE\n" +
            "EAAA8RcNMTkxMjI0MDY0NjAwWjAMMAoGA1UdFQQDCgEJMCMCBBAAAPoXDTE5MTIy\n" +
            "NDA4MTcyMlowDDAKBgNVHRUEAwoBBDAjAgQQAAD7Fw0xOTEyMjQwODE3MjJaMAww\n" +
            "CgYDVR0VBAMKAQQwIwIEEAABChcNMTkxMjI1MDEzMjM2WjAMMAoGA1UdFQQDCgEE\n" +
            "MCMCBBAAAQsXDTE5MTIyNTAxMzIzNlowDDAKBgNVHRUEAwoBBDAjAgQQAAEOFw0x\n" +
            "OTEyMjUwMTMyNDRaMAwwCgYDVR0VBAMKAQQwIwIEEAABDxcNMTkxMjI1MDEzMjQ0\n" +
            "WjAMMAoGA1UdFQQDCgEEMCMCBBAAATIXDTE5MTIyNTEwNTQwMFowDDAKBgNVHRUE\n" +
            "AwoBCTAjAgQQAAEzFw0xOTEyMjUxMDU0MDBaMAwwCgYDVR0VBAMKAQkwIwIEEAAB\n" +
            "NBcNMTkxMjI1MTEyMjA0WjAMMAoGA1UdFQQDCgEBMCMCBBAAATUXDTE5MTIyNTEx\n" +
            "MjIwNFowDDAKBgNVHRUEAwoBATAjAgQQAAE4Fw0xOTEyMjUxMjA3NDBaMAwwCgYD\n" +
            "VR0VBAMKAQQwIwIEEAABORcNMTkxMjI1MTIwNzQwWjAMMAoGA1UdFQQDCgEEMCMC\n" +
            "BBAAAT4XDTE5MTIyNTEyMTExNVowDDAKBgNVHRUEAwoBBDAjAgQQAAE/Fw0xOTEy\n" +
            "MjUxMjExMTVaMAwwCgYDVR0VBAMKAQQwIwIEEAABTBcNMTkxMjMwMDMzOTAwWjAM\n" +
            "MAoGA1UdFQQDCgEJMCMCBBAAAU0XDTE5MTIzMDAzMzkwMFowDDAKBgNVHRUEAwoB\n" +
            "CTAjAgQQAAFSFw0xOTEyMzEwMzExMTJaMAwwCgYDVR0VBAMKAQMwIwIEEAABUxcN\n" +
            "MTkxMjMxMDMxMTEyWjAMMAoGA1UdFQQDCgEDMCMCBBAAAVQXDTIwMDEwNjA4MDE1\n" +
            "NFowDDAKBgNVHRUEAwoBBTAjAgQQAAFVFw0yMDAxMDYwODAxNTRaMAwwCgYDVR0V\n" +
            "BAMKAQUwFQIEEAABYRcNMTkxMjMwMDgyOTE3WjAVAgQQAAFlFw0xOTEyMzEwMzIx\n" +
            "MDdaMCMCBBAAAWgXDTE5MTIzMTAzMjgyNlowDDAKBgNVHRUEAwoBAzAjAgQQAAF7\n" +
            "Fw0xOTEyMzEwMjM1MDBaMAwwCgYDVR0VBAMKAQkwIwIEEAABfBcNMTkxMjMxMDIz\n" +
            "NTAwWjAMMAoGA1UdFQQDCgEJMCMCBBAAAYQXDTIwMDEwMTAzMjEyNFowDDAKBgNV\n" +
            "HRUEAwoBBDAjAgQQAAGFFw0yMDAxMDEwMzIxMjRaMAwwCgYDVR0VBAMKAQQwIwIE\n" +
            "EAABiBcNMjAwMTAxMDc1NDIxWjAMMAoGA1UdFQQDCgEEMCMCBBAAAYkXDTIwMDEw\n" +
            "MTA3NTQyMVowDDAKBgNVHRUEAwoBBDAjAgQQAAGKFw0yMDAxMDEwNzU1MTBaMAww\n" +
            "CgYDVR0VBAMKAQQwIwIEEAABixcNMjAwMTAxMDc1NTEwWjAMMAoGA1UdFQQDCgEE\n" +
            "MCMCBBAAAYwXDTIwMDEwMTA3NTYwNFowDDAKBgNVHRUEAwoBBDAjAgQQAAGNFw0y\n" +
            "MDAxMDEwNzU2MDRaMAwwCgYDVR0VBAMKAQQwIwIEEAABkhcNMjAwMTAxMDgwMjAw\n" +
            "WjAMMAoGA1UdFQQDCgEJMCMCBBAAAZMXDTIwMDEwMTA4MDIwMFowDDAKBgNVHRUE\n" +
            "AwoBCTAjAgQQAAGjFw0yMDAxMDIwOTE0MjRaMAwwCgYDVR0VBAMKAQQwIwIEEAAB\n" +
            "pBcNMjAwMTAyMDkxNDI0WjAMMAoGA1UdFQQDCgEEMCMCBBAAAacXDTIwMDEwMzAy\n" +
            "MzA0N1owDDAKBgNVHRUEAwoBBDAjAgQQAAGoFw0yMDAxMDMwMjMwNDdaMAwwCgYD\n" +
            "VR0VBAMKAQQwIwIEEAABrRcNMjAwMTAzMDI0NjU1WjAMMAoGA1UdFQQDCgEEMCMC\n" +
            "BBAAAa4XDTIwMDEwMzAyNDY1NVowDDAKBgNVHRUEAwoBBDAjAgQQAAGvFw0yMDAx\n" +
            "MDMwMjQ4MjlaMAwwCgYDVR0VBAMKAQQwIwIEEAABsBcNMjAwMTAzMDI0ODI5WjAM\n" +
            "MAoGA1UdFQQDCgEEMCMCBBAAAh0XDTIwMDEwNTA3MzkwOFowDDAKBgNVHRUEAwoB\n" +
            "ATAjAgQQAAIeFw0yMDAxMDUwNzM5MDhaMAwwCgYDVR0VBAMKAQEwIwIEEAACLRcN\n" +
            "MjAwMTA1MDczOTM5WjAMMAoGA1UdFQQDCgEBMCMCBBAAAi4XDTIwMDEwNTA3Mzkz\n" +
            "OVowDDAKBgNVHRUEAwoBATAjAgQQAAI9Fw0yMDAxMDUwNzQ0NDJaMAwwCgYDVR0V\n" +
            "BAMKAQEwIwIEEAACPhcNMjAwMTA1MDc0NDQyWjAMMAoGA1UdFQQDCgEBMCMCBBAA\n" +
            "AlEXDTIwMDEwNTA3NDYyMFowDDAKBgNVHRUEAwoBATAjAgQQAAJSFw0yMDAxMDUw\n" +
            "NzQ2MjBaMAwwCgYDVR0VBAMKAQEwIwIEEAACZRcNMjAwMTA1MDc1MTQ0WjAMMAoG\n" +
            "A1UdFQQDCgEBMCMCBBAAAmYXDTIwMDEwNTA3NTE0NFowDDAKBgNVHRUEAwoBATAj\n" +
            "AgQQAAJ5Fw0yMDAxMDUwNzU2MjJaMAwwCgYDVR0VBAMKAQEwIwIEEAACehcNMjAw\n" +
            "MTA1MDc1NjIyWjAMMAoGA1UdFQQDCgEBMCMCBBAAAosXDTIwMDEwNTA3NTc1OVow\n" +
            "DDAKBgNVHRUEAwoBATAjAgQQAAKMFw0yMDAxMDUwNzU3NTlaMAwwCgYDVR0VBAMK\n" +
            "AQEwIwIEEAACnRcNMjAwMTA1MDc1OTI3WjAMMAoGA1UdFQQDCgEBMCMCBBAAAp4X\n" +
            "DTIwMDEwNTA3NTkyN1owDDAKBgNVHRUEAwoBATAjAgQQAAKvFw0yMDAxMDUwODA4\n" +
            "NTNaMAwwCgYDVR0VBAMKAQEwIwIEEAACsBcNMjAwMTA1MDgwODUzWjAMMAoGA1Ud\n" +
            "FQQDCgEBMCMCBBAAAssXDTIwMDEwNTA4MTAzM1owDDAKBgNVHRUEAwoBATAjAgQQ\n" +
            "AALMFw0yMDAxMDUwODEwMzNaMAwwCgYDVR0VBAMKAQEwIwIEEAAC8RcNMjAwMTA1\n" +
            "MDgxMjI4WjAMMAoGA1UdFQQDCgEBMCMCBBAAAvIXDTIwMDEwNTA4MTIyOFowDDAK\n" +
            "BgNVHRUEAwoBATAjAgQQAAMPFw0yMDAxMDUwODI1NDRaMAwwCgYDVR0VBAMKAQEw\n" +
            "IwIEEAADEBcNMjAwMTA1MDgyNTQ0WjAMMAoGA1UdFQQDCgEBMCMCBBAAAxcXDTIw\n" +
            "MDEwNTA4MzUwMFowDDAKBgNVHRUEAwoBATAjAgQQAAMYFw0yMDAxMDUwODM1MDBa\n" +
            "MAwwCgYDVR0VBAMKAQEwIwIEEAADJRcNMjAwMTA1MDgzNjI1WjAMMAoGA1UdFQQD\n" +
            "CgEBMCMCBBAAAyYXDTIwMDEwNTA4MzYyNVowDDAKBgNVHRUEAwoBATAjAgQQAANJ\n" +
            "Fw0yMDAxMDUwODM5MzhaMAwwCgYDVR0VBAMKAQEwIwIEEAADShcNMjAwMTA1MDgz\n" +
            "OTM4WjAMMAoGA1UdFQQDCgEBMCMCBBAAA3cXDTIwMDEwNTA4NDA1NVowDDAKBgNV\n" +
            "HRUEAwoBATAjAgQQAAN4Fw0yMDAxMDUwODQwNTVaMAwwCgYDVR0VBAMKAQEwIwIE\n" +
            "EAADqxcNMjAwMTA1MTAyMTE4WjAMMAoGA1UdFQQDCgEEMCMCBBAAA6wXDTIwMDEw\n" +
            "NTEwMjExOFowDDAKBgNVHRUEAwoBBDAjAgQQAAOvFw0yMDAxMDUwOTExNTVaMAww\n" +
            "CgYDVR0VBAMKAQEwIwIEEAADsBcNMjAwMTA1MDkxMTU1WjAMMAoGA1UdFQQDCgEB\n" +
            "MCMCBBAABWMXDTIwMDEwNjA5MDUyN1owDDAKBgNVHRUEAwoBBDAjAgQQAAVkFw0y\n" +
            "MDAxMDYwOTA1MjdaMAwwCgYDVR0VBAMKAQQwIwIEEAAFbxcNMjAwMTA2MTAzNTA3\n" +
            "WjAMMAoGA1UdFQQDCgEEMCMCBBAABXAXDTIwMDEwNjEwMzUwN1owDDAKBgNVHRUE\n" +
            "AwoBBDAjAgQQAAVxFw0yMDAxMDYxMDM1MDdaMAwwCgYDVR0VBAMKAQEwIwIEEAAF\n" +
            "chcNMjAwMTA2MTAzNTA3WjAMMAoGA1UdFQQDCgEBMCMCBBAABX0XDTIwMDEwNjEw\n" +
            "NTgxNVowDDAKBgNVHRUEAwoBATAjAgQQAAV+Fw0yMDAxMDYxMDU4MTVaMAwwCgYD\n" +
            "VR0VBAMKAQEwIwIEEAAFtRcNMjAwMTA2MTA1ODUxWjAMMAoGA1UdFQQDCgEBMCMC\n" +
            "BBAABbYXDTIwMDEwNjEwNTg1MVowDDAKBgNVHRUEAwoBATAjAgQQAAXrFw0yMDAx\n" +
            "MDYxMTEwNThaMAwwCgYDVR0VBAMKAQEwIwIEEAAF7BcNMjAwMTA2MTExMDU4WjAM\n" +
            "MAoGA1UdFQQDCgEBMCMCBBAABiMXDTIwMDEwNjExMTEzM1owDDAKBgNVHRUEAwoB\n" +
            "ATAjAgQQAAYkFw0yMDAxMDYxMTExMzNaMAwwCgYDVR0VBAMKAQEwIwIEEAAGWRcN\n" +
            "MjAwMTA2MTEyMDExWjAMMAoGA1UdFQQDCgEBMCMCBBAABloXDTIwMDEwNjExMjAx\n" +
            "MVowDDAKBgNVHRUEAwoBATAjAgQQAAaRFw0yMDAxMDYxMTIwNDZaMAwwCgYDVR0V\n" +
            "BAMKAQEwIwIEEAAGkhcNMjAwMTA2MTEyMDQ2WjAMMAoGA1UdFQQDCgEBMCMCBBAA\n" +
            "BscXDTIwMDEwNjExMjI0OFowDDAKBgNVHRUEAwoBATAjAgQQAAbIFw0yMDAxMDYx\n" +
            "MTIyNDhaMAwwCgYDVR0VBAMKAQEwIwIEEAAG/xcNMjAwMTA2MTEyMzIzWjAMMAoG\n" +
            "A1UdFQQDCgEBMCMCBBAABwAXDTIwMDEwNjExMjMyM1owDDAKBgNVHRUEAwoBATAj\n" +
            "AgQQAAc1Fw0yMDAxMDYxMTMzMjRaMAwwCgYDVR0VBAMKAQEwIwIEEAAHNhcNMjAw\n" +
            "MTA2MTEzMzI0WjAMMAoGA1UdFQQDCgEBMCMCBBAAB20XDTIwMDEwNjExMzQwMFow\n" +
            "DDAKBgNVHRUEAwoBATAjAgQQAAduFw0yMDAxMDYxMTM0MDBaMAwwCgYDVR0VBAMK\n" +
            "AQEwIwIEEAAHoxcNMjAwMTA2MTEzOTM5WjAMMAoGA1UdFQQDCgEBMCMCBBAAB6QX\n" +
            "DTIwMDEwNjExMzkzOVowDDAKBgNVHRUEAwoBATAjAgQQAAfbFw0yMDAxMDYxMTQw\n" +
            "MTVaMAwwCgYDVR0VBAMKAQEwIwIEEAAH3BcNMjAwMTA2MTE0MDE1WjAMMAoGA1Ud\n" +
            "FQQDCgEBMCMCBBAACBUXDTIwMDEwNjEyMjIxOFowDDAKBgNVHRUEAwoBATAjAgQQ\n" +
            "AAgWFw0yMDAxMDYxMjIyMThaMAwwCgYDVR0VBAMKAQEwIwIEEAAISRcNMjAwMTA2\n" +
            "MTIyMjUxWjAMMAoGA1UdFQQDCgEBMCMCBBAACEoXDTIwMDEwNjEyMjI1MVowDDAK\n" +
            "BgNVHRUEAwoBATAjAgQQAAjLFw0yMDAxMDcwMTE3MDNaMAwwCgYDVR0VBAMKAQEw\n" +
            "IwIEEAAIzBcNMjAwMTA3MDExNzAzWjAMMAoGA1UdFQQDCgEBMCMCBBAACP8XDTIw\n" +
            "MDEwNzAxMTczNVowDDAKBgNVHRUEAwoBATAjAgQQAAkAFw0yMDAxMDcwMTE3MzVa\n" +
            "MAwwCgYDVR0VBAMKAQEwIwIEEAAJZRcNMjAwMTA3MDMxNTQ5WjAMMAoGA1UdFQQD\n" +
            "CgEEMCMCBBAACWYXDTIwMDEwNzAzMTU0OVowDDAKBgNVHRUEAwoBBDAjAgQQAAln\n" +
            "Fw0yMDAxMDcwMzE1NTBaMAwwCgYDVR0VBAMKAQEwIwIEEAAJaBcNMjAwMTA3MDMx\n" +
            "NTUwWjAMMAoGA1UdFQQDCgEBMCMCBBAACXUXDTIwMDEwNzAzMTc0N1owDDAKBgNV\n" +
            "HRUEAwoBATAjAgQQAAl2Fw0yMDAxMDcwMzE3NDdaMAwwCgYDVR0VBAMKAQEwIwIE\n" +
            "EAAJkxcNMjAwMTA3MDMxODA1WjAMMAoGA1UdFQQDCgEBMCMCBBAACZQXDTIwMDEw\n" +
            "NzAzMTgwNVowDDAKBgNVHRUEAwoBATAjAgQQAAnDFw0yMDAxMDcwMzE4MzNaMAww\n" +
            "CgYDVR0VBAMKAQEwIwIEEAAJxBcNMjAwMTA3MDMxODMzWjAMMAoGA1UdFQQDCgEB\n" +
            "MCMCBBAACg8XDTIwMDEwNzAzNDIwMFowDDAKBgNVHRUEAwoBBDAjAgQQAAoQFw0y\n" +
            "MDAxMDcwMzQyMDBaMAwwCgYDVR0VBAMKAQQwIwIEEAAKERcNMjAwMTA3MDM0MjAx\n" +
            "WjAMMAoGA1UdFQQDCgEBMCMCBBAAChIXDTIwMDEwNzAzNDIwMVowDDAKBgNVHRUE\n" +
            "AwoBATAjAgQQAAoTFw0yMDAxMDcwNDQ2MTVaMAwwCgYDVR0VBAMKAQQwIwIEEAAK\n" +
            "FBcNMjAwMTA3MDQ0NjE1WjAMMAoGA1UdFQQDCgEEMCMCBBAAChUXDTIwMDEwNzA0\n" +
            "NDYxNlowDDAKBgNVHRUEAwoBATAjAgQQAAoWFw0yMDAxMDcwNDQ2MTZaMAwwCgYD\n" +
            "VR0VBAMKAQEwIwIEEAAKIxcNMjAwMTA3MDQ0ODQ1WjAMMAoGA1UdFQQDCgEBMCMC\n" +
            "BBAACiQXDTIwMDEwNzA0NDg0NVowDDAKBgNVHRUEAwoBATAjAgQQAApBFw0yMDAx\n" +
            "MDcwNDQ5MDVaMAwwCgYDVR0VBAMKAQEwIwIEEAAKQhcNMjAwMTA3MDQ0OTA1WjAM\n" +
            "MAoGA1UdFQQDCgEBMCMCBBAACnEXDTIwMDEwNzA0NDkzNFowDDAKBgNVHRUEAwoB\n" +
            "ATAjAgQQAApyFw0yMDAxMDcwNDQ5MzRaMAwwCgYDVR0VBAMKAQEwIwIEEAAKvRcN\n" +
            "MjAwMTA3MDQ1NjMzWjAMMAoGA1UdFQQDCgEEMCMCBBAACr4XDTIwMDEwNzA0NTYz\n" +
            "M1owDDAKBgNVHRUEAwoBBDAjAgQQAAq/Fw0yMDAxMDcwNDU2MzNaMAwwCgYDVR0V\n" +
            "BAMKAQEwIwIEEAAKwBcNMjAwMTA3MDQ1NjMzWjAMMAoGA1UdFQQDCgEBMCMCBBAA\n" +
            "Cs0XDTIwMDEwNzA0NTkwMlowDDAKBgNVHRUEAwoBATAjAgQQAArOFw0yMDAxMDcw\n" +
            "NDU5MDJaMAwwCgYDVR0VBAMKAQEwIwIEEAAK6xcNMjAwMTA3MDQ1OTIwWjAMMAoG\n" +
            "A1UdFQQDCgEBMCMCBBAACuwXDTIwMDEwNzA0NTkyMFowDDAKBgNVHRUEAwoBATAj\n" +
            "AgQQAAsbFw0yMDAxMDcwNDU5NDlaMAwwCgYDVR0VBAMKAQEwIwIEEAALHBcNMjAw\n" +
            "MTA3MDQ1OTQ5WjAMMAoGA1UdFQQDCgEBMCMCBBAAC2cXDTIwMDEwNzA2MTgxOFow\n" +
            "DDAKBgNVHRUEAwoBBDAjAgQQAAtoFw0yMDAxMDcwNjE4MThaMAwwCgYDVR0VBAMK\n" +
            "AQQwIwIEEAALaRcNMjAwMTA3MDYxODE5WjAMMAoGA1UdFQQDCgEBMCMCBBAAC2oX\n" +
            "DTIwMDEwNzA2MTgxOVowDDAKBgNVHRUEAwoBATAjAgQQAAt3Fw0yMDAxMDcwNjIw\n" +
            "MzZaMAwwCgYDVR0VBAMKAQEwIwIEEAALeBcNMjAwMTA3MDYyMDM2WjAMMAoGA1Ud\n" +
            "FQQDCgEBMCMCBBAAC5UXDTIwMDEwNzA2MjA1NFowDDAKBgNVHRUEAwoBATAjAgQQ\n" +
            "AAuWFw0yMDAxMDcwNjIwNTRaMAwwCgYDVR0VBAMKAQEwIwIEEAALxRcNMjAwMTA3\n" +
            "MDYyMTIzWjAMMAoGA1UdFQQDCgEBMCMCBBAAC8YXDTIwMDEwNzA2MjEyM1owDDAK\n" +
            "BgNVHRUEAwoBATAjAgQQAAwRFw0yMDAxMDcwNjI1MDVaMAwwCgYDVR0VBAMKAQQw\n" +
            "IwIEEAAMEhcNMjAwMTA3MDYyNTA1WjAMMAoGA1UdFQQDCgEEMCMCBBAADBMXDTIw\n" +
            "MDEwNzA2MjUwNlowDDAKBgNVHRUEAwoBATAjAgQQAAwUFw0yMDAxMDcwNjI1MDZa\n" +
            "MAwwCgYDVR0VBAMKAQEwIwIEEAAMIRcNMjAwMTA3MDYyNzMzWjAMMAoGA1UdFQQD\n" +
            "CgEBMCMCBBAADCIXDTIwMDEwNzA2MjczM1owDDAKBgNVHRUEAwoBATAjAgQQAAw/\n" +
            "Fw0yMDAxMDcwNjI3NTBaMAwwCgYDVR0VBAMKAQEwIwIEEAAMQBcNMjAwMTA3MDYy\n" +
            "NzUwWjAMMAoGA1UdFQQDCgEBMCMCBBAADG8XDTIwMDEwNzA2MjgyMFowDDAKBgNV\n" +
            "HRUEAwoBATAjAgQQAAxwFw0yMDAxMDcwNjI4MjBaMAwwCgYDVR0VBAMKAQEwIwIE\n" +
            "EAANKxcNMjAwMTA3MDcyNTM0WjAMMAoGA1UdFQQDCgEEMCMCBBAADSwXDTIwMDEw\n" +
            "NzA3MjUzNFowDDAKBgNVHRUEAwoBBDAjAgQQAA0tFw0yMDAxMDcwNzI1MzVaMAww\n" +
            "CgYDVR0VBAMKAQEwIwIEEAANLhcNMjAwMTA3MDcyNTM1WjAMMAoGA1UdFQQDCgEB\n" +
            "MCMCBBAADTsXDTIwMDEwNzA3MjgwOFowDDAKBgNVHRUEAwoBATAjAgQQAA08Fw0y\n" +
            "MDAxMDcwNzI4MDhaMAwwCgYDVR0VBAMKAQEwIwIEEAANWRcNMjAwMTA3MDcyODI3\n" +
            "WjAMMAoGA1UdFQQDCgEBMCMCBBAADVoXDTIwMDEwNzA3MjgyN1owDDAKBgNVHRUE\n" +
            "AwoBATAjAgQQAA2JFw0yMDAxMDcwNzI4NTZaMAwwCgYDVR0VBAMKAQEwIwIEEAAN\n" +
            "ihcNMjAwMTA3MDcyODU2WjAMMAoGA1UdFQQDCgEBMCMCBBAADdUXDTIwMDEwNzA3\n" +
            "MzIzN1owDDAKBgNVHRUEAwoBBDAjAgQQAA3WFw0yMDAxMDcwNzMyMzdaMAwwCgYD\n" +
            "VR0VBAMKAQQwIwIEEAAN1xcNMjAwMTA3MDczMjM4WjAMMAoGA1UdFQQDCgEBMCMC\n" +
            "BBAADdgXDTIwMDEwNzA3MzIzOFowDDAKBgNVHRUEAwoBATAjAgQQAA3lFw0yMDAx\n" +
            "MDcwNzM1MTFaMAwwCgYDVR0VBAMKAQEwIwIEEAAN5hcNMjAwMTA3MDczNTExWjAM\n" +
            "MAoGA1UdFQQDCgEBMCMCBBAADgMXDTIwMDEwNzA3MzUyOVowDDAKBgNVHRUEAwoB\n" +
            "ATAjAgQQAA4EFw0yMDAxMDcwNzM1MjlaMAwwCgYDVR0VBAMKAQEwIwIEEAAOMxcN\n" +
            "MjAwMTA3MDczNTU4WjAMMAoGA1UdFQQDCgEBMCMCBBAADjQXDTIwMDEwNzA3MzU1\n" +
            "OFowDDAKBgNVHRUEAwoBATAjAgQQAA5/Fw0yMDAxMDcwODE2MzZaMAwwCgYDVR0V\n" +
            "BAMKAQQwIwIEEAAOgBcNMjAwMTA3MDgxNjM2WjAMMAoGA1UdFQQDCgEEMCMCBBAA\n" +
            "DoEXDTIwMDEwNzA4MTYzNlowDDAKBgNVHRUEAwoBATAjAgQQAA6CFw0yMDAxMDcw\n" +
            "ODE2MzZaMAwwCgYDVR0VBAMKAQEwIwIEEAAOjxcNMjAwMTA3MDgxOTA1WjAMMAoG\n" +
            "A1UdFQQDCgEBMCMCBBAADpAXDTIwMDEwNzA4MTkwNVowDDAKBgNVHRUEAwoBATAj\n" +
            "AgQQAA6tFw0yMDAxMDcwODE5MjRaMAwwCgYDVR0VBAMKAQEwIwIEEAAOrhcNMjAw\n" +
            "MTA3MDgxOTI0WjAMMAoGA1UdFQQDCgEBMCMCBBAADt0XDTIwMDEwNzA4MTk1Mlow\n" +
            "DDAKBgNVHRUEAwoBATAjAgQQAA7eFw0yMDAxMDcwODE5NTJaMAwwCgYDVR0VBAMK\n" +
            "AQEwIwIEEAAPORcNMjAwMTA3MTEzOTU5WjAMMAoGA1UdFQQDCgEEMCMCBBAADzoX\n" +
            "DTIwMDEwNzExMzk1OVowDDAKBgNVHRUEAwoBBDAjAgQQAA87Fw0yMDAxMDcxMTQw\n" +
            "MDBaMAwwCgYDVR0VBAMKAQEwIwIEEAAPPBcNMjAwMTA3MTE0MDAwWjAMMAoGA1Ud\n" +
            "FQQDCgEBMCMCBBAAD0kXDTIwMDEwNzExNDIzNFowDDAKBgNVHRUEAwoBATAjAgQQ\n" +
            "AA9KFw0yMDAxMDcxMTQyMzRaMAwwCgYDVR0VBAMKAQEwIwIEEAAPZxcNMjAwMTA3\n" +
            "MTE0MjU3WjAMMAoGA1UdFQQDCgEBMCMCBBAAD2gXDTIwMDEwNzExNDI1N1owDDAK\n" +
            "BgNVHRUEAwoBATAjAgQQAA+XFw0yMDAxMDcxMTQzMjVaMAwwCgYDVR0VBAMKAQEw\n" +
            "IwIEEAAPmBcNMjAwMTA3MTE0MzI1WjAMMAoGA1UdFQQDCgEBMCMCBBAAD+0XDTIw\n" +
            "MDEwNzEzMDEwNlowDDAKBgNVHRUEAwoBBDAjAgQQAA/uFw0yMDAxMDcxMzAxMDZa\n" +
            "MAwwCgYDVR0VBAMKAQQwIwIEEAAP7xcNMjAwMTA3MTI1OTUzWjAMMAoGA1UdFQQD\n" +
            "CgEEMCMCBBAAD/AXDTIwMDEwNzEyNTk1M1owDDAKBgNVHRUEAwoBBDAjAgQQAA/x\n" +
            "Fw0yMDAxMDcxMzAwMDBaMAwwCgYDVR0VBAMKAQkwIwIEEAAP8hcNMjAwMTA3MTMw\n" +
            "MDAwWjAMMAoGA1UdFQQDCgEJMCMCBBAAD/MXDTIwMDEwNzEzMDEwMFowDDAKBgNV\n" +
            "HRUEAwoBCTAjAgQQAA/0Fw0yMDAxMDcxMzAxMDBaMAwwCgYDVR0VBAMKAQkwIwIE\n" +
            "EAAP9RcNMjAwMTA3MTMwMTAwWjAMMAoGA1UdFQQDCgEJMCMCBBAAD/YXDTIwMDEw\n" +
            "NzEzMDEwMFowDDAKBgNVHRUEAwoBCTAjAgQQAA/3Fw0yMDAxMDcxMzAwMjNaMAww\n" +
            "CgYDVR0VBAMKAQQwIwIEEAAP+BcNMjAwMTA3MTMwMDIzWjAMMAoGA1UdFQQDCgEE\n" +
            "MCMCBBAAD/kXDTIwMDEwNzEzMDEzNlowDDAKBgNVHRUEAwoBAzAjAgQQAA/6Fw0y\n" +
            "MDAxMDcxMzAxMzZaMAwwCgYDVR0VBAMKAQMwIwIEEAAP+xcNMjAwMTEwMDEzMzI5\n" +
            "WjAMMAoGA1UdFQQDCgEBMCMCBBAAD/wXDTIwMDExMDAxMzMyOVowDDAKBgNVHRUE\n" +
            "AwoBATAjAgQQAA/9Fw0yMDAxMDcxMzAyNDVaMAwwCgYDVR0VBAMKAQQwIwIEEAAQ\n" +
            "AxcNMjAwMTA3MTMwNjAwWjAMMAoGA1UdFQQDCgEEMCMCBBAAEBQXDTIwMDEwODAy\n" +
            "MDAzMlowDDAKBgNVHRUEAwoBBDAjAgQQABAVFw0yMDAxMDgwMjAwMzJaMAwwCgYD\n" +
            "VR0VBAMKAQQwIwIEEAAQJRcNMjAwMTA4MDM0NTAwWjAMMAoGA1UdFQQDCgEJMCMC\n" +
            "BBAAECYXDTIwMDEwODAzNDUwMFowDDAKBgNVHRUEAwoBCTAjAgQQABAuFw0yMDAx\n" +
            "MTAwMTMyNTBaMAwwCgYDVR0VBAMKAQGgazBpMAsGA1UdFAQEAgIDdzBaBgNVHSME\n" +
            "UzBRgBR27elv4yMF6AebppqAZP4bQUf336EzpDEwLzELMAkGA1UEBhMCY24xDDAK\n" +
            "BgNVBAoMA1JTQTESMBAGA1UEAwwJ5qC5Q0ExMTIxggQQAAAAMA0GCSqGSIb3DQEB\n" +
            "CwUAA4IBAQB17pVI/Eaj/fPvOLgbYJJM+CMMqNBpvj0yYrswT4DzJXfnxvd5ictf\n" +
            "zhFdoDoU05LBOyLYHDDuDZ2EoH+Z/x06v4O0wV9EL6x/D5b6pDbi0nbm0UqquiOK\n" +
            "XF4x4NzpPlunvmN3QlJogB0VoKDcTYR0KZaUDASd4Ccz9ngw1RqdsVLxHlCWaOMJ\n" +
            "cogjVxhPEHJDnC5Y44cNJSKxOmXqC02KbyfaHEQ44IIYAwvMnStPlIeXhvnstMR7\n" +
            "f5G7fdJPn/t9G9ysmNX0M54X216MVocl+XqaBw2K096CgB2exwVut+Kli2s1zTQl\n" +
            "Uaa25ti4Wmh5UVeuw0BlUN3aYpMBRmIW\n" +
            "-----END X509 CRL-----\n";
    @Test
    public void should_be_success_when_given_base64() throws Exception {
        CrlUtil.getX509CRLByBase64(base64Str);
    }

    @Test
    public void should_get_snList_when_give_X509() throws Exception{
        X509CRL crl = CrlUtil.getX509CRLByBase64(base64Str);
        List list  = CrlUtil.getRevokeCertSnList(crl);
        Assert.assertTrue(list.contains("10000017"));
    }
}