package ml.demidko.subweb.data.types

class NormalizedPosition(s: String) :
  Set<String> by s
    .split(' ', '-')
    .filterNot(String::isBlank)
    .map(String::lowercase)
    .filterNot({ it in setOf("по", "в", "над") })
    .toSet()