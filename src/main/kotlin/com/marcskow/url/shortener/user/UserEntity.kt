package com.marcskow.url.shortener.user

import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import org.jetbrains.annotations.NotNull
import javax.persistence.*
import javax.validation.constraints.Size

@Entity
@Table(name = "users")
data class UserEntity(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
                @NotNull @Size(max = 64) val username: String = "",
                @NotNull val password: String = "",
                @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
                @JoinColumn(name = "user_id") val roles: List<RoleEntity>)

@Entity
@Table(name = "roles")
data class RoleEntity(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
                      @NotNull @Size(max = 64) val role: String = "")


/*

@JoinColumn is probably not needed

@ManyToOne part as below is needed only if child is coupled with parent like post and post-comment

@Entity
@Table(name = "roles")
data class RoleEntity(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
                      @NotNull @Size(max = 64) val role: String = "",
                      @ManyToOne(fetch = FetchType.EAGER, optional = false)
                      @JoinColumn(name = "user_id", nullable = false)
                      @OnDelete(action = OnDeleteAction.CASCADE)
                      val user: UserEntity)
 */