package ml.demidko.subweb.text


class TrimmedLowercase(s: String) : CharSequence by s.trim().lowercase()