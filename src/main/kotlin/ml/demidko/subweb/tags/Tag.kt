package ml.demidko.subweb.tags

import ml.demidko.subweb.data.types.NormalizedName
import ml.demidko.subweb.data.types.RussianNumber
import java.io.Serializable

/**
 * Атомарная единица информационной системы SubWeb
 */
sealed interface Tag : Serializable {
  class Phone(val number: RussianNumber) : Tag
  class Person(val name: NormalizedName) : Tag
  class Organization(val name: NormalizedName) : Tag
  class Note(val text: String) : Tag
}