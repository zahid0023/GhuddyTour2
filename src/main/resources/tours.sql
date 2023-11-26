create table if not exists public.users
(
    id        bigserial
    primary key,
    user_name varchar(255)
    );

alter table public.users
    owner to postgres;

create table if not exists public.place_near_by
(
    id               bigserial
    primary key,
    place_name       varchar(255),
    created_by       varchar(255),
    created_at       timestamp,
    deleted          boolean,
    last_modified_by varchar(255),
    updated_at       timestamp,
    version          integer
    );

alter table public.place_near_by
    owner to postgres;

create table if not exists public.activity_types
(
    id                 bigserial
    primary key,
    created_by         varchar(255),
    created_at         timestamp,
    deleted            boolean,
    last_modified_by   varchar(255),
    updated_at         timestamp,
    version            integer,
    active             boolean,
    activity_type_name varchar(255) not null,
    description        text
    );

alter table public.activity_types
    owner to postgres;

create table if not exists public.activities
(
    id                bigserial
    primary key,
    created_by        varchar(255),
    created_at        timestamp,
    deleted           boolean,
    last_modified_by  varchar(255),
    updated_at        timestamp,
    version           integer,
    active            boolean,
    activity_name     varchar(255)              not null,
    average_rating    numeric(2, 1) default 0.0 not null,
    number_of_reviews bigint        default 0   not null,
    short_location    varchar(255)              not null,
    activity_type_id  bigint                    not null
    references public.activity_types
    );

alter table public.activities
    owner to postgres;

create table if not exists public.activity_images
(
    id               bigserial
    primary key,
    created_by       varchar(255),
    created_at       timestamp,
    deleted          boolean,
    last_modified_by varchar(255),
    updated_at       timestamp,
    version          integer,
    active           boolean,
    caption          text,
    file_name        varchar(255)          not null,
    image_url        text                  not null,
    is_display_image boolean default false,
    rejection_reason text,
    update_request   boolean default false not null,
    activity_id      bigint                not null
    references public.activities
    );

alter table public.activity_images
    owner to postgres;

create table if not exists public.added_tours
(
    id                      bigserial
    primary key,
    created_by              varchar(255),
    created_at              timestamp,
    deleted                 boolean,
    last_modified_by        varchar(255),
    updated_at              timestamp,
    version                 integer,
    active                  boolean,
    number_of_days          bigint not null,
    number_of_nights        bigint not null,
    short_address           varchar(255),
    tour_name               varchar(255),
    tour_tag                varchar(255),
    destination_location_id bigint not null
    references public.place_near_by
    );

alter table public.added_tours
    owner to postgres;

create table if not exists public.tours
(
    id               bigserial
    primary key,
    created_by       varchar(255),
    created_at       timestamp,
    deleted          boolean,
    last_modified_by varchar(255),
    updated_at       timestamp,
    version          integer,
    active           boolean,
    description      text         not null,
    thumb_image_url  text,
    title            varchar(255) not null,
    added_tour_id    bigint       not null
    references public.added_tours
    );

alter table public.tours
    owner to postgres;

create table if not exists public.tour_itineraries
(
    id               bigserial
    primary key,
    created_by       varchar(255),
    created_at       timestamp,
    deleted          boolean,
    last_modified_by varchar(255),
    updated_at       timestamp,
    version          integer,
    active           boolean,
    activity_id      bigint not null
    references public.activities,
    tour_id          bigint not null
    references public.tours
    );

alter table public.tour_itineraries
    owner to postgres;

create table if not exists public.tour_specialities
(
    id               bigserial
    primary key,
    created_by       varchar(255),
    created_at       timestamp,
    deleted          boolean,
    last_modified_by varchar(255),
    updated_at       timestamp,
    version          integer,
    active           boolean,
    description      text         not null,
    icon_url         text         not null,
    title            varchar(255) not null,
    update_request   boolean default false,
    tour_id          bigint       not null
    references public.tours
    );

alter table public.tour_specialities
    owner to postgres;

create table if not exists public.tour_accommodation_types
(
    id                      bigserial
    primary key,
    created_by              varchar(255),
    created_at              timestamp,
    deleted                 boolean,
    last_modified_by        varchar(255),
    updated_at              timestamp,
    version                 integer,
    active                  boolean default true not null,
    accommodation_type_name varchar(255)         not null
    );

alter table public.tour_accommodation_types
    owner to postgres;

create table if not exists public.tour_accommodations
(
    id                    bigserial
    primary key,
    created_by            varchar(255),
    created_at            timestamp,
    deleted               boolean,
    last_modified_by      varchar(255),
    updated_at            timestamp,
    version               integer,
    active                boolean,
    accommodation_name    varchar(255)              not null,
    accommodation_type_id bigint                    not null
    references public.tour_accommodation_types,
    accommodation_address varchar(255)              not null,
    average_rating        numeric(3, 1) default 0.0 not null,
    total_reviews         integer       default 0   not null
    );

alter table public.tour_accommodations
    owner to postgres;

create table if not exists public.tour_room_categories
(
    id                 bigserial
    primary key,
    created_by         varchar(255),
    created_at         timestamp,
    deleted            boolean,
    last_modified_by   varchar(255),
    updated_at         timestamp,
    version            integer,
    active             boolean default true not null,
    room_category_name varchar(255)         not null,
    description        text
    );

alter table public.tour_room_categories
    owner to postgres;

create table if not exists public.tour_room_types
(
    id               bigserial
    primary key,
    created_by       varchar(255),
    created_at       timestamp,
    deleted          boolean,
    last_modified_by varchar(255),
    updated_at       timestamp,
    version          integer,
    active           boolean default true not null,
    room_type_name   varchar(255)         not null,
    description      text
    );

alter table public.tour_room_types
    owner to postgres;

create table if not exists public.food_items
(
    id               bigserial
    primary key,
    created_by       varchar(255),
    created_at       timestamp,
    deleted          boolean,
    last_modified_by varchar(255),
    updated_at       timestamp,
    version          integer,
    active           boolean default true not null,
    food_item_name   varchar(255)         not null
    );

alter table public.food_items
    owner to postgres;

create table if not exists public.meal_types
(
    id               bigserial
    primary key,
    created_by       varchar(255),
    created_at       timestamp,
    deleted          boolean,
    last_modified_by varchar(255),
    updated_at       timestamp,
    version          integer,
    active           boolean default true not null,
    meal_type_name   varchar(255)         not null
    );

alter table public.meal_types
    owner to postgres;

create table if not exists public.transportation_brands
(
    id               bigserial
    primary key,
    created_by       varchar(255),
    created_at       timestamp,
    deleted          boolean,
    last_modified_by varchar(255),
    updated_at       timestamp,
    version          integer,
    active           boolean default true not null,
    brand_name       varchar(255)         not null
    );

alter table public.transportation_brands
    owner to postgres;

create table if not exists public.transportation_modes
(
    id               bigserial
    primary key,
    created_by       varchar(255),
    created_at       timestamp,
    deleted          boolean,
    last_modified_by varchar(255),
    updated_at       timestamp,
    version          integer,
    active           boolean default true not null,
    mode_name        varchar(255)         not null,
    description      text,
    icon_url         text                 not null
    );

alter table public.transportation_modes
    owner to postgres;

create table if not exists public.transportation_providers
(
    id                           bigserial
    primary key,
    created_by                   varchar(255),
    created_at                   timestamp,
    deleted                      boolean,
    last_modified_by             varchar(255),
    updated_at                   timestamp,
    version                      integer,
    active                       boolean       default true                     not null,
    transportation_provider_name varchar(255)                                   not null,
    hotline_number               varchar(20)   default 'N/A'::character varying not null,
    rating                       numeric(2, 1) default 0.0                      not null,
    total_reviews                integer       default 0                        not null
    );

alter table public.transportation_providers
    owner to postgres;

create table if not exists public.transportation_routes
(
    id                      bigserial
    primary key,
    created_by              varchar(255),
    created_at              timestamp,
    deleted                 boolean,
    last_modified_by        varchar(255),
    updated_at              timestamp,
    version                 integer,
    active                  boolean default true not null,
    source_location_id      bigint               not null
    references public.place_near_by,
    destination_location_id bigint               not null
    references public.place_near_by
    );

alter table public.transportation_routes
    owner to postgres;

create table if not exists public.subscribed_tours
(
    id                   bigserial
    primary key,
    created_by           varchar(255),
    created_at           timestamp,
    deleted              boolean,
    last_modified_by     varchar(255),
    updated_at           timestamp,
    version              integer,
    active               boolean          default true not null,
    tour_id              bigint                        not null
    references public.tours,
    merchant_id          bigint                        not null
    references public.users,
    tour_reporting_time  time                          not null,
    tour_reporting_place varchar(100)                  not null,
    number_of_reviews    integer          default 0    not null,
    rating_in_stars      double precision default 0.0  not null
    );

alter table public.subscribed_tours
    owner to postgres;

create table if not exists public.subscribed_tour_itineraries
(
    id                  bigserial
    primary key,
    created_by          varchar(255),
    created_at          timestamp,
    deleted             boolean,
    last_modified_by    varchar(255),
    updated_at          timestamp,
    version             integer,
    active              boolean default true not null,
    subscribed_tour_id  bigint               not null
    references public.subscribed_tours,
    activity_id         bigint               not null
    references public.activities,
    activity_day_number integer              not null,
    activity_start_time time                 not null,
    activity_end_time   time                 not null
    );

alter table public.subscribed_tour_itineraries
    owner to postgres;

create table if not exists public.accommodation_packages
(
    id                   bigserial
    primary key,
    created_by           varchar(255),
    created_at           timestamp,
    deleted              boolean,
    last_modified_by     varchar(255),
    updated_at           timestamp,
    version              integer,
    active               boolean default true not null,
    room_category_id     bigint               not null
    references public.tour_room_categories,
    room_type_id         bigint               not null
    references public.tour_room_types,
    accommodation_id     bigint               not null
    references public.tour_accommodations,
    is_shareable         boolean default true not null,
    suitable_for_persons integer              not null,
    bed_count            integer default 1    not null,
    bed_configuration    varchar(100)         not null,
    per_night_room_price numeric(10, 2)       not null,
    subscribed_tour_id   bigint               not null
    references public.subscribed_tours
    );

alter table public.accommodation_packages
    owner to postgres;

create table if not exists public.meal_packages
(
    id                 bigserial
    primary key,
    created_by         varchar(255),
    created_at         timestamp,
    deleted            boolean,
    last_modified_by   varchar(255),
    updated_at         timestamp,
    version            integer,
    active             boolean,
    meal_type_id       bigint         not null
    references public.meal_types,
    per_meal_price     numeric(10, 2) not null,
    subscribed_tour_id bigint         not null
    references public.subscribed_tours
    );

alter table public.meal_packages
    owner to postgres;

create table if not exists public.meal_package_food_item_mapping
(
    meal_package_id bigint not null
    references public.meal_packages,
    food_item_id    bigint not null
    references public.food_items,
    primary key (meal_package_id, food_item_id)
    );

alter table public.meal_package_food_item_mapping
    owner to postgres;

create table if not exists public.transfer_packages
(
    id                         bigserial
    primary key,
    created_by                 varchar(255),
    created_at                 timestamp,
    deleted                    boolean,
    last_modified_by           varchar(255),
    updated_at                 timestamp,
    version                    integer,
    active                     boolean,
    transportation_mode_id     bigint         not null
    references public.transportation_modes,
    transportation_provider_id bigint         not null
    references public.transportation_providers,
    is_ac                      boolean        not null,
    suitable_for_persons       integer        not null,
    per_vehicle_per_trip_price numeric(10, 2) not null,
    transfer_route             text,
    subscribed_tour_id         bigint         not null
    references public.subscribed_tours,
    trip_type                  varchar(20)
    );

alter table public.transfer_packages
    owner to postgres;

create table if not exists public.transportation_packages
(
    id                                      bigserial
    primary key,
    created_by                              varchar(255),
    created_at                              timestamp,
    deleted                                 boolean,
    last_modified_by                        varchar(255),
    updated_at                              timestamp,
    version                                 integer,
    is_active                               boolean default true not null,
    subscribed_tour_id                      bigint               not null
    references public.subscribed_tours,
    transportation_route_id                 bigint               not null
    references public.transportation_routes,
    transportation_mode_id                  bigint               not null
    references public.transportation_modes,
    transportation_brand_id                 bigint               not null
    references public.transportation_brands,
    transportation_provider_id              bigint               not null
    references public.transportation_providers,
    trip_type                               varchar(20)          not null,
    is_ac                                   boolean              not null,
    per_person_transportation_package_price numeric(10, 2)       not null
    );

alter table public.transportation_packages
    owner to postgres;

create table if not exists public.guide_packages
(
    id                        bigserial
    primary key,
    created_by                varchar(255),
    created_at                timestamp,
    deleted                   boolean,
    last_modified_by          varchar(255),
    updated_at                timestamp,
    version                   integer,
    is_active                 boolean default true not null,
    number_of_guide_for_day   integer              not null,
    total_guide_price_for_day numeric(10, 2)       not null,
    subscribed_tour_id        bigint               not null
    references public.subscribed_tours
    );

alter table public.guide_packages
    owner to postgres;

create table if not exists public.spot_entry_packages
(
    id                 bigserial
    primary key,
    created_by         varchar(255),
    created_at         timestamp,
    deleted            boolean,
    last_modified_by   varchar(255),
    updated_at         timestamp,
    version            integer,
    is_active          boolean default true not null,
    activity_id        bigint               not null
    references public.activities,
    price_per_person   numeric(10, 2)       not null,
    remark             text,
    subscribed_tour_id bigint               not null
    references public.subscribed_tours
    );

alter table public.spot_entry_packages
    owner to postgres;

create table if not exists public.tour_package_types
(
    id                bigserial
    primary key,
    created_by        varchar(255),
    created_at        timestamp,
    deleted           boolean,
    last_modified_by  varchar(255),
    updated_at        timestamp,
    version           integer,
    active            boolean default true not null,
    package_type_name varchar(255)         not null,
    description       text                 not null,
    suitable_for      integer              not null
    );

alter table public.tour_package_types
    owner to postgres;

create table if not exists public.available_tour_packages
(
    id                         bigserial
    primary key,
    created_by                 varchar(255),
    created_at                 timestamp,
    deleted                    boolean,
    last_modified_by           varchar(255),
    updated_at                 timestamp,
    version                    integer,
    status                     varchar(50)           not null,
    tour_package_type_id       bigint                not null
    references public.tour_package_types,
    tour_package_name          varchar(255),
    tour_package_description   text,
    subscribed_tour_id         bigint
    references public.subscribed_tours,
    tour_start_date            date                  not null,
    total_seats                integer               not null,
    bookable_seats             integer               not null,
    is_accommodation_inclusive boolean default false not null,
    is_food_inclusive          boolean default false not null,
    is_transfer_inclusive      boolean default false not null,
    is_guide_inclusive         boolean default false not null,
    is_spot_entry_inclusive    boolean default false not null
    );

alter table public.available_tour_packages
    owner to postgres;

create table if not exists public.available_accommodation_options
(
    id                            bigserial
    primary key,
    created_by                    varchar(255),
    created_at                    timestamp,
    deleted                       boolean,
    last_modified_by              varchar(255),
    updated_at                    timestamp,
    version                       integer,
    available_tour_package_id     bigint               not null
    references public.available_tour_packages,
    total_option_price_per_person numeric(10, 2)       not null,
    is_active                     boolean default true not null
    );

alter table public.available_accommodation_options
    owner to postgres;

create table if not exists public.available_accommodation_packages
(
    id                                bigserial
    primary key,
    created_by                        varchar(255),
    created_at                        timestamp,
    deleted                           boolean,
    last_modified_by                  varchar(255),
    updated_at                        timestamp,
    version                           integer,
    accommodation_package_id          bigint         not null
    references public.accommodation_packages,
    night_number                      integer        not null,
    per_night_room_price              numeric(10, 2) not null,
    available_accommodation_option_id bigint         not null
    constraint available_accommodation_packa_available_accommodation_opti_fkey
    references public.available_accommodation_options
    );

alter table public.available_accommodation_packages
    owner to postgres;

create table if not exists public.available_food_options
(
    id                            bigserial
    primary key,
    created_by                    varchar(255),
    created_at                    timestamp,
    deleted                       boolean,
    last_modified_by              varchar(255),
    updated_at                    timestamp,
    version                       integer,
    available_tour_package_id     bigint               not null
    references public.available_tour_packages,
    total_option_price_per_person numeric(10, 2)       not null,
    is_active                     boolean default true not null
    );

alter table public.available_food_options
    owner to postgres;

create table if not exists public.available_meal_packages
(
    id                       bigserial
    primary key,
    created_by               varchar(255),
    created_at               timestamp,
    deleted                  boolean,
    last_modified_by         varchar(255),
    updated_at               timestamp,
    version                  integer,
    meal_package_id          bigint         not null
    references public.meal_packages,
    meal_package_price       numeric(10, 2) not null,
    available_food_option_id bigint         not null
    references public.available_food_options,
    day_number               integer        not null
    );

alter table public.available_meal_packages
    owner to postgres;

create table if not exists public.available_transfer_options
(
    id                            bigserial
    primary key,
    created_by                    varchar(255),
    created_at                    timestamp,
    deleted                       boolean,
    last_modified_by              varchar(255),
    updated_at                    timestamp,
    version                       integer,
    available_tour_package_id     bigint               not null
    references public.available_tour_packages,
    total_option_price_per_person numeric(10, 2)       not null,
    is_active                     boolean default true not null
    );

alter table public.available_transfer_options
    owner to postgres;

create table if not exists public.available_transfer_packages
(
    id                           bigserial
    primary key,
    created_by                   varchar(255),
    created_at                   timestamp,
    deleted                      boolean,
    last_modified_by             varchar(255),
    updated_at                   timestamp,
    version                      integer,
    transfer_package_id          bigint         not null
    references public.transfer_packages,
    per_vehicle_per_trip_price   numeric(10, 2) not null,
    available_transfer_option_id bigint         not null
    references public.available_transfer_options
    );

alter table public.available_transfer_packages
    owner to postgres;

create table if not exists public.available_transportation_packages
(
    id                                      bigserial
    primary key,
    created_by                              varchar(255),
    created_at                              timestamp,
    deleted                                 boolean,
    last_modified_by                        varchar(255),
    updated_at                              timestamp,
    version                                 integer,
    transportation_package_id               bigint               not null
    constraint available_transportation_package_transportation_package_id_fkey
    references public.transportation_packages,
    transportation_package_price_per_person numeric(10, 2)       not null,
    available_tour_package_id               bigint               not null
    constraint available_transportation_package_available_tour_package_id_fkey
    references public.available_tour_packages,
    is_active                               boolean default true not null
    );

alter table public.available_transportation_packages
    owner to postgres;

create table if not exists public.available_guide_options
(
    id                            bigserial
    primary key,
    created_by                    varchar(255),
    created_at                    timestamp,
    deleted                       boolean,
    last_modified_by              varchar(255),
    updated_at                    timestamp,
    version                       integer,
    available_tour_package_id     bigint               not null
    references public.available_tour_packages,
    total_option_price_per_person numeric(10, 2)       not null,
    is_active                     boolean default true not null
    );

alter table public.available_guide_options
    owner to postgres;

create table if not exists public.available_guide_packages
(
    id                        bigserial
    primary key,
    created_by                varchar(255),
    created_at                timestamp,
    deleted                   boolean,
    last_modified_by          varchar(255),
    updated_at                timestamp,
    version                   integer,
    guide_package_id          bigint         not null
    references public.guide_packages,
    day_number                integer        not null,
    total_guide_package_price numeric(10, 2) not null,
    available_guide_option_id bigint         not null
    references public.available_guide_options
    );

alter table public.available_guide_packages
    owner to postgres;

create table if not exists public.available_spot_entry_options
(
    id                            bigserial
    primary key,
    created_by                    varchar(255),
    created_at                    timestamp,
    deleted                       boolean,
    last_modified_by              varchar(255),
    updated_at                    timestamp,
    version                       integer,
    available_tour_package_id     bigint               not null
    references public.available_tour_packages,
    total_option_price_per_person numeric(10, 2)       not null,
    is_active                     boolean default true not null
    );

alter table public.available_spot_entry_options
    owner to postgres;

create table if not exists public.available_spot_entry_packages
(
    id                             bigserial
    primary key,
    created_by                     varchar(255),
    created_at                     timestamp,
    deleted                        boolean,
    last_modified_by               varchar(255),
    updated_at                     timestamp,
    version                        integer,
    available_spot_entry_option_id bigint         not null
    constraint available_spot_entry_packages_available_spot_entry_option__fkey
    references public.available_spot_entry_options,
    spot_entry_price_per_person    numeric(10, 2) not null,
    spot_entry_package_id          bigint         not null
    references public.spot_entry_packages
    );

alter table public.available_spot_entry_packages
    owner to postgres;

create table if not exists public.available_components_all_options
(
    id                                                  bigserial
    primary key,
    created_by                                          varchar(255),
    created_at                                          timestamp,
    deleted                                             boolean,
    last_modified_by                                    varchar(255),
    updated_at                                          timestamp,
    version                                             integer,
    available_tour_package_id                           bigint                   not null
    references public.available_tour_packages,
    available_accommodation_option_id                   bigint         default 0 not null
    constraint available_components_all_opti_available_accommodation_opti_fkey
    references public.available_accommodation_options,
    available_food_option_id                            bigint         default 0 not null
    references public.available_food_options,
    available_transfer_option_id                        bigint         default 0 not null
    constraint available_components_all_opti_available_transfer_option_id_fkey
    references public.available_tour_packages,
    available_transportation_option_id                  bigint         default 0 not null
    constraint available_components_all_opti_available_transportation_opt_fkey
    references public.available_transportation_packages,
    available_guide_option_id                           bigint         default 0 not null
    references public.available_guide_options,
    available_spot_entry_option_id                      bigint         default 0 not null
    constraint available_components_all_opti_available_spot_entry_option__fkey
    references public.available_spot_entry_options,
    ghuddy_platform_calculated_option_price_per_person  numeric(10, 2) default 0 not null,
    merchant_subsidy_amount_per_person                  numeric(10, 2) default 0 not null,
    net_option_price_per_person_after_merchant_subsidy  numeric(10, 2) default 0 not null,
    ghuddy_platform_commission_amount                   numeric(10, 2) default 0 not null,
    net_option_price_per_person_after_ghuddy_commission numeric(10, 2) default 0 not null,
    ghuddy_website_black_price_per_person               numeric(10, 2) default 0 not null,
    ghuddy_subsidy_amount_per_person                    numeric(10, 2) default 0 not null,
    net_option_price_per_person_after_ghuddy_subsidy    numeric(10, 2) default 0 not null,
    ghuddy_website_red_price_per_person                 numeric(10, 2) default 0 not null,
    payment_gateway_amount                              numeric(10, 2) default 0 not null
    );

alter table public.available_components_all_options
    owner to postgres;

create table if not exists public.available_components_all_options_combinations
(
    id                                                  bigserial
    primary key,
    created_by                                          varchar(255),
    created_at                                          timestamp,
    deleted                                             boolean,
    last_modified_by                                    varchar(255),
    updated_at                                          timestamp,
    version                                             integer,
    available_tour_package_id                           bigint                   not null
    constraint available_components_all_option_available_tour_package_id_fkey1
    references public.available_tour_packages,
    available_accommodation_option_id                   bigint
    constraint available_components_all_opt_available_accommodation_opti_fkey1
    references public.available_accommodation_options,
    available_food_option_id                            bigint
    constraint available_components_all_options__available_food_option_id_fkey
    references public.available_food_options,
    available_transfer_option_id                        bigint
    constraint available_components_all_opt_available_transfer_option_id_fkey1
    references public.available_transfer_options,
    available_transportation_package_id                 bigint
    constraint available_components_all_opt_available_transportation_opt_fkey1
    references public.available_transportation_packages,
    available_guide_option_id                           bigint
    constraint available_components_all_option_available_guide_option_id_fkey1
    references public.available_guide_options,
    available_spot_entry_option_id                      bigint
    constraint available_components_all_opt_available_spot_entry_option__fkey1
    references public.available_spot_entry_options,
    ghuddy_platform_calculated_option_price_per_person  numeric(10, 2) default 0 not null,
    merchant_subsidy_amount_per_person                  numeric(10, 2) default 0 not null,
    net_option_price_per_person_after_merchant_subsidy  numeric(10, 2) default 0 not null,
    ghuddy_platform_commission_amount                   numeric(10, 2) default 0 not null,
    net_option_price_per_person_after_ghuddy_commission numeric(10, 2) default 0 not null,
    ghuddy_website_black_price_per_person               numeric(10, 2) default 0 not null,
    ghuddy_subsidy_amount_per_person                    numeric(10, 2) default 0 not null,
    net_option_price_per_person_after_ghuddy_subsidy    numeric(10, 2) default 0 not null,
    ghuddy_website_red_price_per_person                 numeric(10, 2) default 0 not null,
    payment_gateway_amount                              numeric(10, 2) default 0 not null
    );

alter table public.available_components_all_options_combinations
    owner to postgres;

create table if not exists public.available_components_inclusive_options_combinations
(
    id                                                  bigserial
    primary key,
    created_by                                          varchar(255),
    created_at                                          timestamp,
    deleted                                             boolean,
    last_modified_by                                    varchar(255),
    updated_at                                          timestamp,
    version                                             integer,
    available_tour_package_id                           bigint                   not null
    constraint available_components_inclusive_o_available_tour_package_id_fkey
    references public.available_tour_packages,
    available_accommodation_option_id                   bigint
    constraint available_components_inclusiv_available_accommodation_opti_fkey
    references public.available_accommodation_options,
    available_food_option_id                            bigint
    constraint available_components_inclusive_op_available_food_option_id_fkey
    references public.available_food_options,
    available_transfer_option_id                        bigint
    constraint available_components_inclusiv_available_transfer_option_id_fkey
    references public.available_transfer_options,
    available_guide_option_id                           bigint
    constraint available_components_inclusive_o_available_guide_option_id_fkey
    references public.available_guide_options,
    available_spot_entry_option_id                      bigint
    constraint available_components_inclusiv_available_spot_entry_option__fkey
    references public.available_spot_entry_options,
    ghuddy_platform_calculated_option_price_per_person  numeric(10, 2) default 0 not null,
    merchant_subsidy_amount_per_person                  numeric(10, 2) default 0 not null,
    net_option_price_per_person_after_merchant_subsidy  numeric(10, 2) default 0 not null,
    ghuddy_platform_commission_amount                   numeric(10, 2) default 0 not null,
    net_option_price_per_person_after_ghuddy_commission numeric(10, 2) default 0 not null,
    ghuddy_website_black_price_per_person               numeric(10, 2) default 0 not null,
    ghuddy_subsidy_amount_per_person                    numeric(10, 2) default 0 not null,
    net_option_price_per_person_after_ghuddy_subsidy    numeric(10, 2) default 0 not null,
    ghuddy_website_red_price_per_person                 numeric(10, 2) default 0 not null,
    payment_gateway_amount                              numeric(10, 2) default 0 not null
    );

alter table public.available_components_inclusive_options_combinations
    owner to postgres;

