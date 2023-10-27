INSERT INTO armor_type (id, name)
VALUES (1, 'cloth'),
       (2, 'leather'),
       (3, 'plate');

INSERT INTO weapon_type (id, name)
VALUES (1, 'axe'),
       (2, 'bow'),
       (3, 'crossbow'),
       (4, 'dagger'),
       (5, 'mace'),
       (6, 'magic stuff'),
       (7, 'polearm'),
       (8, 'shield'),
       (9, 'sword');

INSERT INTO slot_type (id, name)
VALUES (1, 'back'),
       (2, 'chest'),
       (3, 'foot'),
       (4, 'hands'),
       (5, 'legs'),
       (6, 'primary weapon'),
       (7, 'secondary weapon'),
       (8, 'utility');

INSERT INTO rarity (id, name, color_code)
VALUES (1, 'poor', '#C8BDAD'),
       (2, 'common', '#FBF3E7'),
       (3, 'uncommon', '#C1FF62'),
       (4, 'rare', '#72BDEB'),
       (5, 'epic', '#C76FFF'),
       (6, 'legend', '#FFC650'),
       (7, 'unique', '#FFFAA0');

INSERT INTO hand_type (id, name)
VALUES (1, 'one-handed'),
       (2, 'two-handed');

INSERT INTO item_option (id, name)
VALUES (1, 'action_speed'),
       (2, 'additional_magical_damage'),
       (3, 'additional_move_speed'),
       (4, 'additional_physical_damage'),
       (5, 'agility'),
       (6, 'all_attribute'),
       (7, 'armor_penetration'),
       (8, 'armor_rating'),
       (9, 'buff_duration_bonus'),
       (10, 'debuff_duration_bonus'),
       (11, 'headshot_damage_reduction'),
       (12, 'item_equip_speed'),
       (13, 'knowledge'),
       (14, 'magical_penetration'),
       (15, 'magic_resistance'),
       (16, 'magical_healing'),
       (17, 'magical_interaction_speed'),
       (18, 'magical_power'),
       (19, 'max_health'),
       (20, 'move_speed_bonus'),
       (21, 'physical_damage_bonus'),
       (22, 'physical_damage_reduction'),
       (23, 'physical_healing'),
       (24, 'projectile_reduction'),
       (25, 'regular_interaction_speed'),
       (26, 'resourcefulness'),
       (27, 'spell_capacity_bonus'),
       (28, 'spell_casting_speed'),
       (29, 'strength'),
       (30, 'true_magical_damage'),
       (31, 'true_physical_damage'),
       (32, 'weapon_damage'),
       (33, 'will'),
       (34, 'strength_static'),
       (35, 'agility_static'),
       (36, 'will_static'),
       (37, 'knowledge_static'),
       (38, 'resourcefulness_static'),
       (39, 'armor_rating_static'),
       (40, 'weapon_damage_static'),
       (41, 'move_speed_static'),
       (42, 'magical_damage_static'),
       (43, 'magic_weapon_damage_static'),
       (44, 'max_health_static');

INSERT
INTO item (id, name, armor_type_id, hand_type_id, slot_type_id, weapon_type_id)
VALUES
    (1, 'rapier', null, 1, 6, 9), (2, 'arming sword', null, 1, 6, 9),
                                  (3, 'falchion', null, 1, 6, 9);

# INSERT INTO auction_item (item_id, rarity_id, action_speed, max_health, agility, price_event_currency, price_golden_key, price_gold, price_gold_ingot, expiration_time, auction_status_type, allow_offer)
# VALUES
#     (1, 2, 4, 2, 3, 0, 0, 300, 0, NOW() + INTERVAL 12 HOUR, 'ACTIVE', true);