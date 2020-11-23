package com.exercise1.useraction

import com.vladmihalcea.hibernate.type.array.IntArrayType
import com.vladmihalcea.hibernate.type.array.StringArrayType
import com.vladmihalcea.hibernate.type.json.JsonBinaryType
import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType
import com.vladmihalcea.hibernate.type.json.JsonNodeStringType
import com.vladmihalcea.hibernate.type.json.JsonStringType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import org.hibernate.annotations.TypeDefs
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table(name = "user_action")
@SequenceGenerator(name = "user_action_seq", sequenceName = "user_action_seq", allocationSize = 1)
@TypeDefs(*[
  TypeDef(name = "string-array", typeClass = StringArrayType::class),
  TypeDef(name = "int-array", typeClass = IntArrayType::class),
  TypeDef(name = "json", typeClass = JsonStringType::class),
  TypeDef(name = "jsonb", typeClass = JsonBinaryType::class),
  TypeDef(name = "jsonb-node", typeClass = JsonNodeBinaryType::class),
  TypeDef(name = "json-node", typeClass = JsonNodeStringType::class)
])
data class UserAction(
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_action_seq")
  val id: Long?,

  @Column(name = "user_id")
  val userId: Long,

  @Enumerated(EnumType.STRING)
  @Column(name = "action")
  val action: ActionEnum,

  @Column(name = "query")
  val query: String,

  @Type(type = "jsonb")
  @Column(name = "data", columnDefinition = "jsonb")
  val actionData: ActionData
)
