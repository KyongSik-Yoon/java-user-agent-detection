package net.pieroxy.ua.detection;
import java.io.*;
import java.util.*;
/**
* A RenderingEngine is made of a Brand, a RenderingEngineFamily and two versions.
*/
public class RenderingEngine { /** The main company behind the browser */
    public Brand vendor;
    /** The general family of the rendering engine. Could be GECKO, TRIDENT, WEBKIT, ... */
    public RenderingEngineFamily family;
    /** The two first numbers in the version of the rendering engine. Ex: 1.7 or 533.17 */
    public String version;
    /** The full version number of the rendering engine. Ex: 1.6.8 or 533.17.9 */
    public String fullVersion;

    /**
     * This is the most detailed constructor of the RenderingEngine object, where everything can be specified.
     * @param  _brand           The vendor of this browser.
     * @param  _family          The family of this browser.
     * @param  _version         The version of this browser.
     * @param  _fullVersion     The full version of this browser.
    */
    public RenderingEngine(Brand _brand, RenderingEngineFamily _family, String _version, String _fullVersion) {
        family = _family;
        vendor = _brand;
        version = _version;
        fullVersion = _fullVersion;
    }


    /**
     * This constructor of the RenderingEngine object only specifies the <code>fullVersion</code>. The <code>version</code> is deduced by calling <code>setFullVersionOneShot(oneVersion)</code>.
     * @param  _brand           The vendor of this browser.
     * @param  _family          The family of this browser.
     * @param  _oneVersion      The full version of this browser.
    */
    public RenderingEngine(Brand _brand, RenderingEngineFamily _family, String _oneVersion) {
        this(_brand, _family, "", "");
        setFullVersionOneShot(_oneVersion);
    }

    /**
     * This constructor of the RenderingEngine object only specifies the <code>fullVersion</code> as a float. The <code>version</code> is deduced by calling <code>setFullVersionOneShot(oneVersion)</code>.
     * @param  _brand           The vendor of this browser.
     * @param  _family          The family of this browser.
     * @param  _oneVersion      The full version of this browser, as a floating-point number.
    */
    public RenderingEngine(Brand _brand, RenderingEngineFamily _family, float _oneVersion) {
        this(_brand, _family, "", "");
        String version = String.valueOf(_oneVersion);
        if (version.indexOf(".")==-1) version += ".0";
        setFullVersionOneShot(version);
    }

    public static RenderingEngine getUnknown() {
        return new RenderingEngine(Brand.UNKNOWN, RenderingEngineFamily.UNKNOWN, "");
    }

    public static RenderingEngine getOther(Brand brand) {
        return new RenderingEngine(brand, RenderingEngineFamily.OTHER, "");
    }

    public static RenderingEngine getText() {
        return new RenderingEngine(Brand.UNKNOWN, RenderingEngineFamily.TEXT, "");
    }

    public static RenderingEngine getNone() {
        return new RenderingEngine(Brand.UNKNOWN, RenderingEngineFamily.NONE, "");
    }

    public String toString() {
        String res = family + " " + version;
        if (fullVersion != null && fullVersion.length()>0) {
            res += " " + fullVersion;
        }
        return res;
    }

    public boolean equals(Object o) {
        if (o == null) return false;
        if (! (o instanceof RenderingEngine)) return false;
        RenderingEngine d = (RenderingEngine) o;
        if (d.family==null && family!=d.family) return false;
        if (d.version==null && version!=d.version) return false;
        if (d.vendor==null && vendor!=d.vendor) return false;
        if (d.fullVersion==null && fullVersion!=d.fullVersion) return false;
        return
            ( (d.family==null && family==null) || d.family.equals(family) ) &&
            ( (d.version==null && version==null) || d.version.equals(version) ) &&
            ( (d.vendor==null && vendor==null) || d.vendor.equals(vendor) ) &&
            ( (d.fullVersion==null && fullVersion==null) || d.fullVersion.equals(fullVersion) ) ;
    }

    public int hashCode() {
        int res = 0;
        if (family != null) {
            res *= 3;
            res += family.hashCode();
        }
        if (vendor!= null) {
            res *= 3;
            res += vendor.hashCode();
        }
        if (version != null) {
            res *= 3;
            res += version.hashCode();
        }
        if (fullVersion != null) {
            res *= 3;
            res += fullVersion.hashCode();
        }
        return res;
    }

    /**
     * This method sets both <code>version</code> and <code>fullVersion</code> attributes of this Browser object.
     * It will set the <code>version</code> as the full version truncated to the first non numeric character, leaving the first '.' character in the mix.
     * @param  version The full version number.
    */
    public void setFullVersionOneShot(String version) {
        this.fullVersion = version;
        String sv = "";
        boolean dot = false;
        for (int i=0 ; i<version.length() ; i++) {
            char c = version.charAt(i);
            if (c == '.') {
                if (dot) break;
                dot = true;
                sv += c;
            } else if (Character.isDigit(c)) {
                sv += c;
            } else break;
        }
        this.version = sv;
    }
}