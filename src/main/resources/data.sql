INSERT INTO armor_type (id, name) VALUES
                                      (1, 'cloth'),
                                      (2, 'leather'),
                                      (3, 'plate');

INSERT INTO weapon_type (id, name) VALUES
                                       (1, 'axe'),
                                       (2, 'bow'),
                                       (3, 'crossbow'),
                                       (4, 'dagger'),
                                       (5, 'mace'),
                                       (6, 'magic stuff'),
                                       (7, 'polearm'),
                                       (8, 'shield'),
                                       (9, 'sword');

INSERT INTO slot_type (id, name) VALUES
                                     (1, 'back'),
                                     (2, 'chest'),
                                     (3, 'foot'),
                                     (4, 'hands'),
                                     (5, 'legs'),
                                     (6, 'primary weapon'),
                                     (7, 'secondary weapon'),
                                     (8, 'utility');

INSERT INTO rarity (id, name) VALUES
                                  (1, 'poor'),
                                  (2, 'common'),
                                  (3, 'uncommon'),
                                  (4, 'rare'),
                                  (5, 'epic'),
                                  (6, 'legend'),
                                  (7, 'unique');

INSERT INTO hand_type (id, name) VALUES
                                     (1, 'one-handed'),
                                     (2, 'two-handed');

INSERT INTO item (id, name, armor_type_id, hand_type_id, slot_type_id, weapon_type_id) VALUES
    (1, 'rapier', null, 1, 6, 9),
    (2, 'arming sword', null, 1, 6, 9),
    (3, 'falchion', null, 1, 6, 9);

