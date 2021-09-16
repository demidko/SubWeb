package ml.demidko.subweb.tags

import ml.demidko.subweb.text.types.TrimmedLowercase

class Name(parts: Set<TrimmedLowercase>) : Tag, Set<TrimmedLowercase> by parts