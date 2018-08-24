package com.marcskow.url.shortener.user

import org.jetbrains.annotations.NotNull
import org.springframework.security.core.GrantedAuthority
import javax.persistence.*
import javax.validation.constraints.Size

@Entity
@Table(name = "users")
data class User(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
                @NotNull @Size(max = 64) val username: String,
                @NotNull val password: String,
                @NotNull val firstName: String,
                @NotNull val lastName: String,
                @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
                @JoinColumn(name = "user_id")
                val roles: List<Role> = listOf(Role(role = RoleValue.USER))
)

@Entity
@Table(name = "roles")
data class Role(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
                @Enumerated(EnumType.STRING) val role: RoleValue)

enum class RoleValue : GrantedAuthority {
    USER, ADMIN;

    override fun getAuthority(): String {
        return name
    }
}

/*

@JoinColumn is probably not needed

@ManyToOne part as below is needed only if child is coupled with parent like post and post-comment

@Entity
@Table(name = "roles")
data class Role(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
                      @NotNull @Size(max = 64) val role: String = "",
                      @ManyToOne(fetch = FetchType.EAGER, optional = false)
                      @JoinColumn(name = "user_id", nullable = false)
                      @OnDelete(action = OnDeleteAction.CASCADE)
                      val user: User)
 */