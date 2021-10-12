package com.company.MMK.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneNumber {

    @Id
    @SequenceGenerator(
            name = "phone_number_sequence",
            sequenceName = "phone_number_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "phone_number_sequence"
    )
    private Long id;

    @Length(max = 40)
    private String number;

    @ManyToOne
    @JoinColumn(
            name = "account_id",
            referencedColumnName = "id"
    )
    private Account account;

}
