package com.example.as.Model.spec;

import java.util.List;

public class Species {
    private int gender_rate;
    private boolean forms_switchable;
    private java.util.List<String> form_descriptions;
    private java.util.List<Flavor_text_entriesEntity> flavor_text_entries;
    private Evolves_from_speciesEntity evolves_from_species;
    private Evolution_chainEntity evolution_chain;
    private java.util.List<Egg_groupsEntity> egg_groups;
    private ColorEntity color;
    private int capture_rate;
    private int base_happiness;

    public int getGender_rate() {
        return gender_rate;
    }

    public boolean isForms_switchable() {
        return forms_switchable;
    }

    public List<String> getForm_descriptions() {
        return form_descriptions;
    }

    public List<Flavor_text_entriesEntity> getFlavor_text_entries() {
        return flavor_text_entries;
    }

    public Evolves_from_speciesEntity getEvolves_from_species() {
        return evolves_from_species;
    }

    public Evolution_chainEntity getEvolution_chain() {
        return evolution_chain;
    }

    public List<Egg_groupsEntity> getEgg_groups() {
        return egg_groups;
    }

    public ColorEntity getColor() {
        return color;
    }

    public int getCapture_rate() {
        return capture_rate;
    }

    public int getBase_happiness() {
        return base_happiness;
    }
}
