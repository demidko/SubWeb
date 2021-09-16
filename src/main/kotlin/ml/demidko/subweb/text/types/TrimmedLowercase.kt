package ml.demidko.subweb.text.types


class TrimmedLowercase(s: String) : CharSequence by s.trim().lowercase()