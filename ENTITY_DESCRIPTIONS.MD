cats =>
    cat_id (STRING) (UNIQUE) (PK)
    weight (DOUBLE)
    name (STRING)
    visits (INT)
    date_of_birth (LOCALDATE)
    gender (ENUM): MALE, FEMALE
    is_hospitalized (BOOLEAN)
    photo (URL-STRING)
    belonging_family_id (STRING) => FK (*->1) => owning_family.family_id

medications_and_treats =>
    product_id (STRING) (UNIQUE) (PK)
    price (DOUBLE)
    title (STRING)
    weeks_after_birth_to_start_medication (INT)
    market_release_date (LOCALDATE)
    medication_type (ENUM): PILLS, DROPS, OINTMENT, TREAT 
    is_prescription_needed (BOOLEAN)
    can_be_gifted_on_cats_birthday (BOOLEAN)
    photo (URL-STRING)

owning_family =>
    family_id (STRING) (UNIQUE) (PK)
    living_space_square_meters (DOUBLE)
    amount_of_kids (INT)
    family_last_name (STRING)
    owner_persons_phone (STRING)
    owners_backup_persons_phone (STRING)
    registration_date (LOCALDATE)
    smokes_at_home (BOOLEAN)
    prefered_communication_language (ENUM): NL, FR, DE, EN, OTHER
    photo_of_persons_id (URL-STRING)
    photo_of_backup_person_id (URL-STRING)
