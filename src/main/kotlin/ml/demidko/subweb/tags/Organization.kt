package ml.demidko.subweb.tags

import ml.demidko.subweb.text.types.TrimmedLowercase

class Organization(name: Set<TrimmedLowercase>) : Tag, Set<TrimmedLowercase> by name