package com.example.demo.common.entity;

import com.example.demo.common.BaseEntity;
import com.example.demo.user.domain.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "archives", schema = "test")
@AttributeOverrides({
        @AttributeOverride(name = "createdAt", column = @Column(name = "created_at", nullable = false)),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "updated_at"))
})
public class Archive extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @NotNull
    @Column(name = "position_x", nullable = false, precision = 10, scale = 2)
    private BigDecimal positionX;

    @NotNull
    @Column(name = "position_y", nullable = false, precision = 10, scale = 2)
    private BigDecimal positionY;

    @Size(max = 200)
    @Column(name = "address", length = 500)
    private String address;

    @Size(max = 200)
    @Column(name = "name", length = 200)
    private String name;

    @Size(max = 2000)
    @Column(name = "content", length = 2000)
    private String content;

    @NotNull
    @Column(name = "is_public", nullable = false)
    private Boolean isPublic = false;

    @OneToMany(mappedBy = "archive")
    private Set<ArchiveAttach> archiveAttaches = new LinkedHashSet<>();

    @OneToMany(mappedBy = "archive")
    private Set<ArchiveComment> archiveComments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "archive")
    private Set<ArchiveReaction> archiveReactions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "archive")
    private Set<ArchiveTag> archiveTags = new LinkedHashSet<>();

}