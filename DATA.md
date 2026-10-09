git p## Scheme description

The schema models cats and their owners:
The cats entity stores data about each cat (weight, name, date of birth, hospitalization status, etc.) and links directly to its owner via owner_that_cat_belongs_to -> cat_owner.cat_owner_id.
The cat_owner entity describes the owners themselves (living space, number of kids, preferred communication language, whether they smoke at home, etc.).
The medication entity contains medication with its pricing, type, and prescription requirements, not directly linked to the other entities, but has many-to-many relationship with cats

cats * - * medication
cats * - 1 cat_owner

## cats

- cat_id (STRING) (UNIQUE) (PK)
- weight (DOUBLE)
- name (STRING)
- visits (INT)
- date_of_birth (LOCALDATE)
- gender (ENUM): MALE, FEMALE
- is_hospitalized (BOOLEAN)
- photo (URL-STRING)
- owner_that_cat_belongs_to (STRING) => FK (*->1) => cat_owner.cat_owner_id

## medications

- product_id (STRING) (UNIQUE) (PK)
- price (DOUBLE)
- title (STRING)
- weeks_after_birth_to_start_medication (INT)
- market_release_date (LOCALDATE)
- medication_type (ENUM): PILLS, OINTMENT, DROPS, SYRUP, POWDER, INJECTION
- is_prescription_needed (BOOLEAN)
- can_be_gifted_on_cats_birthday (BOOLEAN)
- photo (URL-STRING)

## cat_owner

- cat_owner_id (STRING) (UNIQUE) (PK)
- living_space_square_meters (DOUBLE)
- amount_of_kids (INT)
- full_name (STRING)
- phone_number (STRING)
- registration_date (LOCALDATE)
- smokes_at_home (BOOLEAN)
- prefered_communication_language (ENUM): NL, FR, DE, EN, OTHER
- photo_of_owners_id_card (URL-STRING)