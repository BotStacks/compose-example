package util

import platform.Foundation.NSBundle
import platform.Foundation.NSDictionary
import platform.Foundation.create

fun <T> readPlist(name: String, key: String): T? {
    val bundle = NSBundle.mainBundle.pathForResource(name, "plist") ?: return null

    val plist = NSDictionary.create(contentsOfFile = bundle)
    return readPlist(plist, key)
}

fun <T> readPlist(plist: NSDictionary?, key: String): T? {
    return plist?.objectForKey(key) as? T
}