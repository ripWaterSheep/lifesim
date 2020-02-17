package game.ECS.components;

/** Implementations define certain related characteristics of an entity.
 * If an entity does not have a component, then the entity does not have its characteristics
 * and will not be included in subsystem processes that need the component. (ex: Entities without a health component
 * will not take any damage in the collision system)
 */
public interface IComponent {

    IComponent copy();

}
