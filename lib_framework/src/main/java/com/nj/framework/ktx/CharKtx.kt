package com.nj.framework.ktx

/**
 * 通过检查字符的 Unicode 类型以及是否落在特定的 Emoji Unicode 范围内来确定字符是否为 Emoji
 */
fun Char.isEmojiCharacter(): Boolean {
    val type = Character.getType(this)
    return type == Character.SURROGATE.toInt() || isEmoji()
}

/**
 * 判断字符是否属于 Emoji 范围
 */
fun Char.isEmoji(): Boolean {
    // 基本多文种平面内的 Emoji 范围
    if (this.code in 0x1F600..0x1F64F // 表情符号
        || this.code in 0x1F300..0x1F5FF // 杂项符号和象形文字
        || this.code in 0x1F680..0x1F6FF // 运输和地图符号
        || this.code in 0x2600..0x26FF // 杂项符号
        || this.code in 0x2700..0x27BF // 丁巴特符号
        || this.code in 0xFE00..0xFE0F // 变音标记
        || this.code in 0x1F900..0x1F9FF// 补充符号和象形文字
    ) {
        return true
    }
    // 补充多文种平面内的 Emoji 范围
    return this.code in 0x1F1E6..0x1F1FF
}
