package ml.demidko.subweb.data.types

class NormalizedUrl(s: String) :
  CharSequence by s
    .trim(' ', '/')
    .lowercase()
    .replaceFirst("https://", "")
    .replaceFirst("http://", "")
    .replaceFirst("www.", "")