package com.subhakar.spring.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "post")
@Table(name = "post_details")
public class PostDetails {
    @Id
    private Long id;

    private Date createdOn;
    private String createdBy;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @MapsId
    @JoinColumn(name = "id")
    private Post post;
}
